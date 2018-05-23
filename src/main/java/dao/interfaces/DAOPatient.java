package dao.interfaces;

import model.Patient;

import java.sql.SQLException;
import java.util.List;

public interface DAOPatient {
    Patient read(String key) throws SQLException;

    List<Patient> getAllPatients() throws SQLException;
}
