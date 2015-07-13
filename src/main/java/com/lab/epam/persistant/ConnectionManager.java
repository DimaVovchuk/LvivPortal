package com.lab.epam.persistant;

import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class ConnectionManager {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

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
        loger.info("getConnection method");
        return instance;
    }

    private static void instance() throws SQLException {
        ConnectionPool connectionPool = new ConnectionPool(10);
        instance = connectionPool;
    }

}
