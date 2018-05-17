import dao.implementations.DAOFactoryImpl;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {

    @Test
    public void testGetConnection() throws SQLException {
        DAOFactoryImpl daoFactory = DAOFactoryImpl.getInstance();
        Connection connection = daoFactory.getConnection();
        Assert.assertNotNull(connection);
    }
}
