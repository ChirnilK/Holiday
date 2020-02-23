package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Registration {

    private Connection conn;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public Registration(Connection mainConn) {
        conn = mainConn;
    }

    public String registerCustomer() {                      // register new customer in the customers table. return social security number that is input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Customer's name?");
        String customer_name = scanner.nextLine();
        System.out.println("Input social security number");
        String social_secnr = scanner.nextLine();
        System.out.println("And phone number");
        String telephonenr = scanner.nextLine();
        try {
            statement = conn.prepareStatement("INSERT INTO customers(customer_name,social_secnr, telephonenr) VALUES (?,?,?)");
            statement.setString(1, customer_name);
            statement.setString(2, social_secnr);
            statement.setString(3, telephonenr);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return social_secnr;
    }


    public ResultSet searchCustomerBySocialSecnr(String social_secnr) {  //using the social-sec-number, get that customer's info.
        try {
            statement = conn.prepareStatement("SELECT * FROM customers WHERE social_secnr=?");
            statement.setString(1, social_secnr);
            return statement.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public void customerPrintResult(ResultSet resultSet) {            //print out the new customer's info
        try {
            while (resultSet.next()) {
                String row = "Customer_id: " + resultSet.getInt("customer_id")
                        + ",  Name: " + resultSet.getString("customer_name")
                        + ",  Social security number: " + resultSet.getString("social_secnr")
                        + ",  Phone number: " + resultSet.getString("telephonenr") + ".";
                System.out.println(row);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
