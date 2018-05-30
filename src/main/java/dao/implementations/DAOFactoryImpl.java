package dao.implementations;

import dao.interfaces.*;
//import org.apache.log4j.Logger;

import java.sql.*;

/**
 * This class describes implementation of the Factory, which we use to connect to DB.
 * This connection is unique, because we use Singleton pattern.
 *
 * @author sirko
 * * */
public class DAOFactoryImpl implements DAOFactory {
    private static DAOFactoryImpl instance = null;

//    private static final Logger logger = Logger.getLogger(DAOFactoryImpl.class);

    public static DAOFactoryImpl getInstance() {
        if (DAOFactoryImpl.instance == null)
            DAOFactoryImpl.instance = new DAOFactoryImpl();

        return DAOFactoryImpl.instance;
    }

    private DAOFactoryImpl() {
        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
//            logger.error(ex);
            ex.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        String user = "root";
        String password = "123789Cthusq";
        String url = "jdbc:mysql://localhost:3306/hospitalDB?useUnicode=true&characterEncoding=utf8";
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public DAODoctorImpl getDAODoctorImpl(Connection connection) {
        return new DAODoctorImpl(connection);
    }

    @Override
    public DAOHumanImpl getDAOHumanImpl(Connection connection) {
        return new DAOHumanImpl(connection);
    }

    @Override
    public DAONurseImpl getDAONurseImpl(Connection connection) {
        return new DAONurseImpl(connection);
    }

    @Override
    public DAOPatientImpl getDAOPatientImpl(Connection connection) {
        return new DAOPatientImpl(connection);
    }
}
