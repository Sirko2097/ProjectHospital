package dao.interfaces;

import model.Doctor;

import java.sql.SQLException;

public interface DAODoctor {
    Doctor read(String key) throws SQLException;

    boolean docLogin(String licenseNumber, String password) throws SQLException;

}
