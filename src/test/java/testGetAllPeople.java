import dao.implementations.DAOFactoryImpl;
import dao.implementations.DAOPatientImpl;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class testGetAllPeople {
    @Test
    public void testGetAllPatients() throws SQLException {
        DAOFactoryImpl daoFactory = DAOFactoryImpl.getInstance();
        Connection connection = daoFactory.getConnection();
        DAOPatientImpl daoPatient = daoFactory.getDAOPatientImpl(connection);

        Assert.assertNotNull(daoPatient.getAllPatients());
    }

}
