package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.SortedMap;

public class Registration {

    private Connection conn;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public Registration(Connection mainConn) {
        conn = mainConn;
    }

    public String registerCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input customer's id");
        int customer_id = Integer.parseInt(scanner.nextLine());
        System.out.println("Customer's name?");
        String customer_name = scanner.nextLine();
        System.out.println("Input social security number");
        String social_secnr = scanner.nextLine();
        System.out.println("And phone number");
        String telephonenr = scanner.nextLine();
        try {
            statement = conn.prepareStatement("INSERT INTO customers(customer_id,customer_name,social_secnr, telephonenr) VALUES (?,?,?,?)");
            statement.setInt(1, customer_id);
            statement.setString(2, customer_name);
            statement.setString(3, social_secnr);
            statement.setString(4, telephonenr);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return social_secnr;
    }


    public ResultSet searchBySocialSecnr(String social_secnr) {
        try {
            statement = conn.prepareStatement("SELECT * FROM customers WHERE social_secnr=?");
            statement.setString(1, social_secnr);
            return statement.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public void printResult(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                String row = "Customer_id: " + resultSet.getInt("customer_id")
                        + ", Name: " + resultSet.getString("customer_name")
                        + ", Social security number: " + resultSet.getString("social_secnr")
                        + ", Phone number: " + resultSet.getString("telephonenr") + ".";
                System.out.println(row);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
