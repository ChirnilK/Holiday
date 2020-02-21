package com.company;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class CancellingRescheduling {


    private Connection conn;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public CancellingRescheduling(Connection mainConn) {
        conn = mainConn;
    }

    public void deleteBook(int book_id){
        try {
            statement = conn.prepareStatement("delete from bookings where book_id = ?");
            statement.setInt(1, book_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet selectBookings() {
        try {
            statement = conn.prepareStatement("SELECT * FROM bookings");
            return statement.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}


