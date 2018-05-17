package dao.interfaces;

import model.Human;

import java.sql.SQLException;

public interface DAOHuman {
    Human read(String key) throws SQLException;
}
