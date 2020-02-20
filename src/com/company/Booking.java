package com.company;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Booking {


    private Connection conn;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public Booking(Connection mainConn) {
        conn = mainConn;
    }

}


