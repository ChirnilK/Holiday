package com.company;
import java.sql.*;

import java.util.Scanner;

public class SqlConsole {

    private Connection conn = null;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public SqlConsole(){
        connect();
    }

    public void connect(){
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/holidaymaker?user=root&password=mysql&serverTimezone=UTC");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String registerCustomer(){
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


    public void searchBySocialSecnr(String social_secnr){
        try {
            statement = conn.prepareStatement("SELECT * FROM customers WHERE social_secnr=?");
            statement.setString(1,social_secnr);
            resultSet = statement.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void printResult() {
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
