package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SearchingRoom {


    private Connection conn;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public SearchingRoom(Connection mainConn) {
        conn = mainConn;
    }

    public String searchRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== What kind of room are you looking for ===");
        System.out.println("How many are you? Select a number");
        int numberOfPeople = Integer.parseInt(scanner.nextLine());
        System.out.println("Please select a number for");
        System.out.println("1: Single / 2: Double / 3: Twin / 4: Family /5:Executive suite room ");
        int room_id = Integer.parseInt(scanner.nextLine());
        System.out.println("When is your check in date? Input ex; 2020-01-30");
        String checkInDate = scanner.nextLine();
        System.out.println("When is your check out date? Input ex; 2020-01-30");
        String checkOutDate = scanner.nextLine();


        try {
            statement = conn.prepareStatement("select hotelroom_id, hotel_name, room_type from booked_list"+ "\n" +
                    "where NOT(check_in=? BETWEEN check_in AND check_out" +"\n" +
                    "OR check_out=? BETWEEN check_in AND check_out)" + "\n" +
                    "and room_id=?");

            statement.setString(1, checkInDate);
            statement.setString(2, checkOutDate);
            statement.setInt(3, room_id);
            resultSet = statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String row="";

        try{
            while (resultSet.next()) {
                row = "Hotelroom id: " + resultSet.getInt("hotelroom_id")
                        + ", Hotel name: " + resultSet.getString("hotel_name")
                        + ", Room type: " + resultSet.getString("room_type");
                System.out.println("You can book this/these hotel(s) between " +checkInDate+ " and " + checkOutDate);
                System.out.println(row);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return row;
    }
}
