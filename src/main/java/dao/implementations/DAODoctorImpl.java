package dao.implementations;

import dao.interfaces.DAODoctor;
import model.Doctor;
import model.Patient;

import java.sql.*;
import java.util.List;

public class DAODoctorImpl implements DAODoctor {
    private final Connection connection;

    public DAODoctorImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Doctor read(String key) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT\n" +
                "  `license_number`,\n" +
                "  DOCTOR.`passport_number`,\n" +
                "  `first_name`,\n" +
                "  `second_name`,\n" +
                "  `last_name`,\n" +
                "  `birthday`\n" +
                "FROM DOCTOR\n" +
                "  JOIN HUMAN H on DOCTOR.passport_number = H.passport_number " +
                "WHERE `license_number`='" + key + "'");
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String licenseNUmber = resultSet.getString(1);
            String passNumber = resultSet.getString(2);
            String firstName = resultSet.getString(3);
            String secondName = resultSet.getString(4);
            String lastName = resultSet.getString(5);
            Date birthday = resultSet.getDate(6);
            return new Doctor(passNumber, firstName, secondName, lastName, birthday, licenseNUmber);
        } else {
            return null;
        }
    }

    /**
     * This method returns all patients who the doctor treats
     * @param licenseNumber - license number of doctor
     * @param cardNumber - card number of patient
     * @return list of patients
     * */
    @Override
    public List<Patient> getAllPatients(String licenseNumber, String cardNumber) throws SQLException {
        return null;
    }

    /**
     * This method checks the existing of doctor in DB(hospital)
     *
     * @param licenseNumber - number of doctor's license
     * @param password - password fot accessing
     * */
    @Override
    public boolean docLogin(String licenseNumber, String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT license_number, password " +
                "FROM DOCTOR WHERE license_number=? AND password=?");
        preparedStatement.setString(1, licenseNumber);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
}
