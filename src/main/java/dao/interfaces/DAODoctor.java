package dao.interfaces;

import model.Doctor;
import model.Patient;

import java.sql.SQLException;
import java.util.List;

public interface DAODoctor {
    Doctor read(String key) throws SQLException;

    boolean docLogin(String licenseNumber, String password) throws SQLException;

    List<Patient> getAllHistoryOfPatients(String licenseNumber) throws SQLException;
}
