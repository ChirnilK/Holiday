package com.company;
import java.sql.*;



public class SqlConsole {

    private Connection conn = null;

    public SqlConsole() {
        connect();
    }

    public Connection getConn() {
        return conn;
    }

    public void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/holidaymaker?user=root&password=mysql&serverTimezone=UTC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
