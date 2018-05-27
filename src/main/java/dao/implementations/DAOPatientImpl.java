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
                "AND PATIENT.passport_number ='AT 567890'");

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
    public void AddNewPatient(String passNumber, String firstName,  String secondName, String lastName,
                              java.util.Date birthday, int cardNumber) throws SQLException{
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " +
                    "HUMAN (passport_number, first_name, second_name, last_name, birthday) " +
                    "VALUES ('"+ passNumber + "','"+ firstName + "', '" + secondName +"', '"
                    + lastName +"', birthday)");
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("INSERT INTO PATIENT (passport_number, card_number) " +
                    "VALUES ('" + passNumber + "'," + cardNumber+ " )");
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
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
}
