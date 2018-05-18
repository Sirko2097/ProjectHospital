package dao.implementations;

import dao.interfaces.DAOPatient;
import model.Human;
import model.Patient;

import java.sql.*;

public class DAOPatientImpl implements DAOPatient {
    Connection connection;

    public DAOPatientImpl(Connection connection) {
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
}
