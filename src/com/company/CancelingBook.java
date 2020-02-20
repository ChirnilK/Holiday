package com.company;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class CancelingBook {


    private Connection conn;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public CancelingBook(Connection mainConn) {
        conn = mainConn;
    }

}


