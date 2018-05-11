package dao.implementations;

import dao.interfaces.*;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactoryImpl implements DAOFactory {
    private static DAOFactoryImpl instance = new DAOFactoryImpl();

    private static final Logger logger = Logger.getLogger(DAOFactoryImpl.class);

    public static DAOFactoryImpl getInstance() {
        return instance;
    }

    private DAOFactoryImpl() {
        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            logger.error(ex);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        String user = "root";
        String password = "123789Cthusq";
        String url = "jdbc:mysql://localhost:3306/hospitalDB";
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public DAODoctor getDAODoctorImpl(Connection connection) {
        return null;
    }

    @Override
    public DAOHuman getDAOHumanImpl(Connection connection) {
        return null;
    }

    @Override
    public DAONurse getDAONurseImpl(Connection connection) {
        return null;
    }

    @Override
    public DAOPatient getDAOPatientImpl(Connection connection) {
        return null;
    }
}
