package dao.interfaces;

import dao.implementations.DAODoctorImpl;
import dao.implementations.DAOHumanImpl;
import dao.implementations.DAONurseImpl;
import dao.implementations.DAOPatientImpl;

import java.sql.Connection;
import java.sql.SQLException;

public interface DAOFactory {
    Connection getConnection() throws SQLException;

    DAODoctorImpl getDAODoctorImpl(Connection connection);

    DAOHumanImpl getDAOHumanImpl(Connection connection);

    DAONurseImpl getDAONurseImpl(Connection connection);

    DAOPatientImpl getDAOPatientImpl(Connection connection);
}
