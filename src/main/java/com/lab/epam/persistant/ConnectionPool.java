package com.lab.epam.persistant;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.Vector;

public class ConnectionPool {
    private Vector<Connection> availableConns = new Vector<Connection>();
    private Vector<Connection> usedConns = new Vector<Connection>();
    private String url;
    private String user;
    private String password;

    public ConnectionPool(int initConnCnt) {
        Properties props = new Properties();

        try {
            URL location = getClass().getProtectionDomain().getCodeSource().getLocation();
            File configFile = new File(location.getFile(),"persistent.xml");
            InputStream stream = new FileInputStream("D:\\JAVA\\Git\\LvivPortal\\src\\main\\resources\\persistent.xml");
            props.loadFromXML(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            final String driver = props.getProperty("jdbc.driver");
            Class.forName(driver);
            props.put("useUnicode", "true");
            props.put("characterEncoding", "utf8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.url = props.getProperty("jdbc.url");
        this.user = props.getProperty("jdbc.user");
        this.password = props.getProperty("jdbc.password");
        for (int i = 0; i < initConnCnt; i++) {
            availableConns.addElement(getConnection());
        }
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public synchronized Connection retrieve() {
        Connection newConn = null;
        if (availableConns.size() == 0) {
            newConn = getConnection();
        } else {
            newConn = (Connection) availableConns.lastElement();
            availableConns.removeElement(newConn);
        }
        usedConns.addElement(newConn);
        return newConn;
    }

    public synchronized void putback(Connection c) throws NullPointerException {
        if (c != null) {
            if (usedConns.removeElement(c)) {
                availableConns.addElement(c);
            } else {
                throw new NullPointerException(
                        "Connection not in the usedConns array");
            }
        }
    }

    public int getAvailableConnsCnt() {
        return availableConns.size();
    }

}
