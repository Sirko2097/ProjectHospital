package dao.implementations;

import dao.interfaces.DAOHuman;
import model.Human;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOHumanImpl implements DAOHuman {
    private final Connection connection;

    public DAOHumanImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Human read(String key) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM HUMAN WHERE passport_number='" + key + "'");
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            Human human = new Human();
            human.setPassportNumber(resultSet.getString("passport_number"));
            human.setFirstName(resultSet.getString("first_name"));
            human.setSecondName(resultSet.getString("second_name"));
            human.setLastName(resultSet.getString("last_name"));
            human.setBirthday(resultSet.getDate("birthday"));

            return human;
        } else {
            return null;
        }
    }
}
