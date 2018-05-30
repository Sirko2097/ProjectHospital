package dao.implementations;

import dao.interfaces.DAONurse;
import model.Doctor;
import model.Nurse;

import java.sql.*;

public class DAONurseImpl implements DAONurse {
    private final Connection connection;

    DAONurseImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Nurse read(String key) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT\n" +
                "  `license_number`,\n" +
                "  NURSE.`passport_number`,\n" +
                "  `first_name`,\n" +
                "  `second_name`,\n" +
                "  `last_name`,\n" +
                "  `birthday`\n" +
                "FROM NURSE\n" +
                "  JOIN HUMAN H on NURSE.passport_number = H.passport_number " +
                "WHERE `license_number`='" + key + "'");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String licenseNUmber = resultSet.getString(1);
            String passNumber = resultSet.getString(2);
            String firstName = resultSet.getString(3);
            String secondName = resultSet.getString(4);
            String lastName = resultSet.getString(5);
            Date birthday = resultSet.getDate(6);
            return new Nurse(passNumber, firstName, secondName, lastName, birthday, licenseNUmber);
        } else {
            return null;
        }
    }

    @Override
    public boolean nurseLogin(String licenseNumber, String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT license_number, password " +
                "FROM NURSE WHERE license_number=? AND password=?");
        preparedStatement.setString(1, licenseNumber);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }
}
