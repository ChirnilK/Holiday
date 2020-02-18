package com.company;
import java.sql.*;

import java.util.Scanner;

public class SqlConsole {

    private Connection conn = null;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public SqlConsole() {
        connect();
    }

    public void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/holidaymaker?user=root&password=mysql&serverTimezone=UTC");
        } catch (Exception e) {
            e.printStackTrace();
        }
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


    public void searchBySocialSecnr(String social_secnr) {
        try {
            statement = conn.prepareStatement("SELECT * FROM customers WHERE social_secnr=?");
            statement.setString(1, social_secnr);
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

    public int searchRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== What kind of room are you looking for ===");
        System.out.println("How many are you? Select a number");
        int numberOfPeople = Integer.parseInt(scanner.nextLine());
        System.out.println("Please select a number for");
        System.out.println("1: Single / 2: Double / 3: Twin / 4: Family /5:Executive suite room ");
        int room_type = Integer.parseInt(scanner.nextLine());
        System.out.println("When is your check in date? Input ex; 2020-01-30");
        String checkInDate = scanner.nextLine();
        System.out.println("When is your check out date? Input ex; 2020-01-30");
        String checkOutDate = scanner.nextLine();

        Integer hotelroom_id = 0;
        try {
            String query = "select hotelroom_id from " +
                    "(select book_id, hotel_name, room_type, hotelroom_id, number_of_people, check_in, check_out " +
                    "from bookings " +
                    "left join rooms on bookings.room_id = rooms.room_id " +
                    "left join hotels on bookings.hotel_id = hotels.hotel_id " +
                    "left join hotelrooms on hotelrooms.room = rooms.room_id " +
                    "and hotelrooms.hotel = hotels.hotel_id) as booked_list " +
                    "where NOT(check_in=? BETWEEN check_in AND check_out " +
                    "OR check_out=? BETWEEN check_in AND check_out) " +
                    "and room_type=?";

                statement = conn.prepareStatement(query.toString());
                statement.setString(1, checkInDate);
                statement.setString(2, checkOutDate);
                statement.setInt(3, room_type);
                try (ResultSet resultSet = statement.executeQuery()) {
                    hotelroom_id = resultSet.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return hotelroom_id;
        }


    public void searchByHotelid(int hotelroom_id){
        try {
            statement = conn.prepareStatement("SELECT * FROM hotelrooms WHERE hotelroom_id=?");
            statement.setInt(1,hotelroom_id);
            resultSet = statement.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void searchResult() {
        try{
            while (resultSet.next()) {
                String row = "Hotelroom_id: " + resultSet.getInt("hotelroom_id")
                        + ", Hotel: " + resultSet.getInt("hotel")
                        + ", Room: " + resultSet.getInt("room");
                System.out.println(row);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
