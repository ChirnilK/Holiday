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

    public void rescheduleCheck(ArrayList info, String checkIn, String checkOut){
        System.out.println("");
        String  hotelid = (String) info.get(5);
        int hotel_id = Integer.parseInt(hotelid);
        String  roomid = (String) info.get(0);
        int room_id = Integer.parseInt(roomid);


        try {
            statement = conn.prepareStatement("SELECT * FROM all_room_booked_and_unbooked \n" +
                    "WHERE room_id = ? and hotel_id = ?\n" +
                    "group by hotelroom_id\n" +
                    "HAVING check_in IS NULL OR check_out <= ? OR check_in >= ?;");

            statement.setInt(1, room_id);
            statement.setInt(2, hotel_id);
            statement.setString(3, checkIn);
            statement.setString(4, checkOut);
            resultSet = statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String row = "";

        try {
            System.out.println("You can rebook this hotel between " + checkIn + " and " + checkOut);
            while (resultSet.next()) {
                row = "Hotelroom id: " + resultSet.getInt("hotelroom_id")
                        + ", Hotel name: " + resultSet.getString("hotel_name")
                        + ", Room type: " + resultSet.getString("room_type")
                        + ", Room price/night: " + resultSet.getDouble("room_price_per_night")
                        + ", Distance to beach: " + resultSet.getDouble("km_to_beach")
                        + ", Distance to city: " + resultSet.getDouble("km_to_city")
                        + ", Guest rating: " + resultSet.getInt("guest_rating")
                        + ", Pool: " + resultSet.getInt("pool")
                        + ", Evening entertainment: " + resultSet.getInt("evening_entertainment")
                        + ", Kids club: " + resultSet.getInt("kids_club")
                        + ", Restaurant: " + resultSet.getInt("restaurant");
                System.out.println(row);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void reschedule(int book_id, String checkIn, String checkOut) {

        try {
            statement = conn.prepareStatement("update bookings set check_in = ? , check_out = ? where book_id = ?;");

            statement.setString(1, checkIn);
            statement.setString(2, checkOut);
            statement.setInt(3, book_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


