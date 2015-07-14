package com.lab.epam.persistant;

import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class ConnectionManager {

    private static ConnectionPool instance = null;

    private ConnectionManager() {
    }

    public synchronized static ConnectionPool getConnection() {
        if (instance == null) {
            try {
                instance();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private static void instance() throws SQLException {
        ConnectionPool connectionPool = new ConnectionPool(10);
        instance = connectionPool;
    }

}
