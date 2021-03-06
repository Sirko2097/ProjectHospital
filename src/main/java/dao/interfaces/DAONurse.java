package dao.interfaces;

import model.Nurse;

import java.sql.SQLException;

public interface DAONurse {
    Nurse read(String key) throws SQLException;

    boolean nurseLogin(String licenseNumber, String password) throws SQLException;
}
