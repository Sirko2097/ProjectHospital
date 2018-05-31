package dao.implementations;

import dao.interfaces.DAOPatient;
import model.Human;
import model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOPatientImpl implements DAOPatient {
    private final Connection connection;

    DAOPatientImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Patient read(String key) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT PATIENT.passport_number, first_name, second_name, " +
                "last_name, birthday, card_number FROM PATIENT " +
                "LEFT JOIN HUMAN H on PATIENT.passport_number = H.passport_number " +
                "AND PATIENT.passport_number ='" + key + "'");

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {

            String passNumber = resultSet.getString(1);
            String firstName = resultSet.getString(2);
            String secondName = resultSet.getString(3);
            String lastName = resultSet.getString(4);
            Date birthDay = resultSet.getDate(5);
            int cardNumber = resultSet.getInt(6);

            return new Patient(passNumber, firstName, secondName, lastName, birthDay, cardNumber);
        } else {
            return null;
        }
    }

    @Override
    public List<String> getListOfPatientParameters(int cardNumber) throws SQLException {
        List<String> listOfPatientParameters = new ArrayList<>();
        int i = 0;
        while (i < 10) {
            listOfPatientParameters.add("");
            i++;
        }

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT " +
                "P.passport_number,\n" +
                "  first_name,\n" +
                "  second_name,\n" +
                "  last_name,\n" +
                "  P.card_number,\n" +
                "  medicine_name,\n" +
                "  procedure_name,\n" +
                "  disease_name,\n" +
                "  date\n" +
                "FROM HUMAN\n" +
                "  JOIN PATIENT P on HUMAN.passport_number = P.passport_number\n" +
                "  JOIN CARD C on P.card_number = C.card_number\n" +
                "  JOIN TREATMENT T on C.treat_id = T.treatment_id\n" +
                "  JOIN TREATMENT_MEDICINE MEDICINE on T.treatment_id = MEDICINE.treat_id\n" +
                "  JOIN TREATMENT_PROCEDURE PROCEDURE2 on T.treatment_id = PROCEDURE2.treat_id\n" +
                "  JOIN CARD_DIAGNOSIS C2 on C.card_number = C2.card_num\n" +
                "  JOIN DIAGNOSIS D on C2.diag_id = D.diagnosis_id\n" +
                "  JOIN DOCTOR_PATIENT PATIENT2 on P.card_number = PATIENT2.patient_card\n" +
                "WHERE P.card_number = ?");
        preparedStatement.setInt(1, cardNumber);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            listOfPatientParameters.set(0, resultSet.getString(1));
            listOfPatientParameters.set(1, resultSet.getString(2));
            listOfPatientParameters.set(2, resultSet.getString(3));
            listOfPatientParameters.set(3, resultSet.getString(4));
            listOfPatientParameters.set(4, resultSet.getString(5));
            listOfPatientParameters.set(5, resultSet.getString(6));
            listOfPatientParameters.set(6, resultSet.getString(7));
            listOfPatientParameters.set(7, resultSet.getString(8));
            listOfPatientParameters.set(8, resultSet.getString(9));
        } else {
            preparedStatement = connection.prepareStatement("SELECT P.passport_number, first_name, second_name, " +
                    "last_name, birthday, card_number " +
                    "FROM HUMAN JOIN PATIENT P on HUMAN.passport_number = P.passport_number " +
                    "WHERE card_number=" + cardNumber);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Patient patient = new Patient(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getDate(5), resultSet.getInt(6));
                listOfPatientParameters.set(0, patient.getPassportNumber());
                listOfPatientParameters.set(1, patient.getFirstName());
                listOfPatientParameters.set(2, patient.getSecondName());
                listOfPatientParameters.set(3, patient.getLastName());
                listOfPatientParameters.set(4, Integer.toString(patient.getPatientCard()));

            }
        }
        return listOfPatientParameters;
    }

    @Override
    public void AddNewPatient(String passNumber, String firstName,  String secondName, String lastName,
                              String birthday, String cardNumber) throws SQLException{
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " +
                    "HUMAN (passport_number, first_name, second_name, last_name, birthday) " +
                    "VALUES ('"+ passNumber + "','"+ firstName + "', '" + secondName +"', '"
                    + lastName +"','" + birthday + "')");
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("INSERT INTO PATIENT (passport_number, card_number) " +
                    "VALUES ('" + passNumber + "'," + Long.parseLong(cardNumber)+ " )");
            preparedStatement.execute();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException();
        }
    }

    @Override
    public List<Patient> getAllPatients() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT " +
                "P.passport_number," +
                "first_name," +
                "second_name," +
                "last_name," +
                "birthday," +
                "card_number" +
                " FROM HUMAN" +
                " JOIN PATIENT P on HUMAN.passport_number = P.passport_number");

        List<Patient> patients = new ArrayList<>();

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            patients.add(new Patient(resultSet.getString(1),
                    resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getDate(5),
                    resultSet.getInt(6)));
        }
        return patients;
    }

    @Override
    public void addDoctor(String license, String patientPassNumber) throws SQLException {
        int cardNumber;

        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT card_number\n" +
                    "FROM PATIENT\n" +
                    "  JOIN HUMAN H on PATIENT.passport_number = H.passport_number\n" +
                    "where H.passport_number = '" + patientPassNumber + "'");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cardNumber =resultSet.getInt(1);
            } else throw new SQLException();

            preparedStatement = connection.prepareStatement("INSERT INTO DOCTOR_PATIENT (license, patient_card) " +
                    "VALUES ('" + license +"', " + cardNumber +")");
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public List<Patient> existing(List<Patient> patients) throws SQLException {
        List<Patient> checkPatient = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT\n" +
                "  H.passport_number,\n" +
                "  first_name,\n" +
                "  second_name,\n" +
                "  last_name,\n" +
                "  birthday,\n" +
                "  patient_card\n" +
                "FROM PATIENT\n" +
                "  LEFT JOIN DOCTOR_PATIENT D on PATIENT.card_number = D.patient_card\n" +
                "  JOIN HUMAN H on PATIENT.passport_number = H.passport_number\n" +
                "where patient_card IS NUll;");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            checkPatient.add(new Patient(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5),
                    resultSet.getInt(6)));
        }

        return checkPatient;
    }
}
