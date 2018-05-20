package dao.implementations;

import java.sql.Connection;

public class DAONurseImpl {
    private final Connection connection;

    public DAONurseImpl(Connection connection) {
        this.connection = connection;
    }


}
