package dao.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface DAOFactory {
    Connection getConnection() throws SQLException;

    DAODoctor getDAODoctorImpl(Connection connection);

    DAOHuman getDAOHumanImpl(Connection connection);

    DAONurse getDAONurseImpl(Connection connection);

    DAOPatient getDAOPatientImpl(Connection connection);
}
