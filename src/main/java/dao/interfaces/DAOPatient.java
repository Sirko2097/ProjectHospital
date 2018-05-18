package dao.interfaces;

import model.Patient;

import java.sql.SQLException;

public interface DAOPatient {
    Patient read(String key) throws SQLException;
}
