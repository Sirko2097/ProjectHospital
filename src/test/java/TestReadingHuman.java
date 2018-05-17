import dao.implementations.DAOFactoryImpl;
import model.Human;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestReadingHuman {
    @Test
    public void testReadingHuman() throws SQLException {
        Connection connection = DAOFactoryImpl.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM HUMAN WHERE passport_number=?");
        preparedStatement.setString(1, "AT 123456");
        ResultSet resultSet = preparedStatement.executeQuery();
        Human human = null;
        if(resultSet.next()) {
            human = new Human();
            human.setPassportNumber(resultSet.getString("passport_number"));
            human.setFirstName(resultSet.getString("first_name"));
            human.setSecondName(resultSet.getString("second_name"));
            human.setLastName(resultSet.getString("last_name"));
            human.setBirthday(resultSet.getDate("birthday"));
        }
        Assert.assertNotNull(human);
    }
}
