package dao.interfaces;

import model.Patient;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface DAOPatient {
    Patient read(String key) throws SQLException;

    List<Patient> getAllPatients() throws SQLException;

    void AddNewPatient(String passNumber, String firstName, String secondName,
                       String lastName, Date birthday, int cardNumber) throws SQLException;
}
