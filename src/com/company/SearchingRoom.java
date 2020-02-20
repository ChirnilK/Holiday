package com.company;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchingRoom {


    private Connection conn;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public SearchingRoom(Connection mainConn) {
        conn = mainConn;
    }

    public ArrayList<String> searchRoom(String purpose) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Searching for your best room ===");
        System.out.println("How many are you? Select a number");
        int numberOfPeople = Integer.parseInt(scanner.nextLine());
        System.out.println("Which room do you want to stay?");
        System.out.println("1: Single room / 2: Double room / 3: Twin room / 4: Family room / 5: Executive suite room");
        int room_id = scanner.nextInt();
        if (numberOfPeople > room_id) {
            System.out.println("The max people in this room is" + numberOfPeople + ". Please change room.");
        }
        else{
            System.out.println("When is your check in date? Input ex; 2020-01-30");
            String checkIn = scanner.next();
            LocalDate checkInDate = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(checkIn));
            LocalDate startOfSeason = LocalDate.of(2020, 6, 1);
            if (checkInDate.isBefore(startOfSeason)) {
                System.out.println("Please call us later!");
            } else {
                System.out.println("When is your check out date? Input ex; 2020-01-30");
                String checkOut = scanner.next();
                LocalDate checkOutDate = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(checkOut));
                LocalDate endOfSeason = LocalDate.of(2020, 7, 30);
                if (checkOutDate.isAfter(endOfSeason)) {
                    System.out.println("Sorry! See you next year!");
                } else {
                    System.out.println(" ====== Popular filters  =======" );
                    System.out.println("Pool? y/n");
                    Scanner filter = new Scanner(System.in);
                    String poolAnswer = filter.nextLine();
                    int pool = 0;
                    if (poolAnswer.equals("y")){
                        pool = 1;
                    }

                    System.out.println("Evening entertainment? y/n");
                    String entertainment = filter.nextLine();
                    int evening_entertainment = 0;
                    if (entertainment.equals("y")){
                        evening_entertainment = 1;
                    }

                    System.out.println("Kids club? y/n");
                    String kids = filter.nextLine();
                    int kids_club = 0;
                    if (kids.equals("y")){
                        kids_club = 1;
                    }

                    System.out.println("Restaurant? y/n");
                    String eat = filter.nextLine();
                    int restaurant = 0;
                    if (eat.equals("y")){
                        restaurant = 1;
                    }

                    double km_to_beach = 100.0;
                    double km_to_city = 100.0;
                    if (purpose.equals("Beach")){
                        km_to_beach = 2.0;
                    }
                    else if (purpose.equals("City")){
                        km_to_city = 2.0;
                    }

                    System.out.println("Input a minimum number for guest-rating");
                    int guest_rating = filter.nextInt();

                    try {
                        statement = conn.prepareStatement("SELECT * FROM all_room_booked_and_unbooked \n" +
                                "WHERE room_id = ? and pool >= ?\n" +
                                "AND kids_club >= ? AND evening_entertainment >= ?\n" +
                                "and restaurant >= ? and  guest_rating >= ?\n" +
                                "and km_to_beach <= ? and km_to_city <= ?\n" +
                                "HAVING check_in IS NULL OR check_out <= ? OR check_in >= ?;");


                        statement.setInt(1, room_id);
                        statement.setInt(2, pool);
                        statement.setInt(3, kids_club);
                        statement.setInt(4, evening_entertainment);
                        statement.setInt(5, restaurant);
                        statement.setInt(6,guest_rating);
                        statement.setDouble(7, km_to_beach);
                        statement.setDouble(8, km_to_city);
                        statement.setString(9, checkOut);
                        statement.setString(10, checkIn);
                        resultSet = statement.executeQuery();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    String row = "";
                    ArrayList<String> result = new ArrayList<>();

                    try {
                        System.out.println("You can book this/these hotel(s) between " + checkInDate + " and " + checkOutDate);
                        String numPeople = String.valueOf(numberOfPeople);
                        result.add(numPeople);
                        result.add(checkIn);
                        result.add(checkOut);
                        String roomid = String.valueOf(room_id);
                        result.add(roomid);
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
                    return result;
                }
            }
        }
    return null;
    }

    public void bookRoom(ArrayList result) {
        int number_of_people = (int) result.get(0);
        String check_in = (String) result.get(1);
        String check_out = (String) result.get(2);
        int room_id = (int) result.get(3);
        System.out.println("Input the Hotelroom_id number for proceeding to book your hotel");
        Scanner sc = new Scanner(System.in);
        int hotel_id = sc.nextInt();
        System.out.println("Family room and Executive suite room can get extra bed, 500kr/bed.");
        System.out.println("Do you want to have one? y/n");
        String extrabed = sc.nextLine();

        System.out.println("Will you have half pension or full pension?");
        System.out.println("h:half pension(500kr/person) / f:full pension(700kr/person) / n: don't need ");
        String pension = sc.nextLine();
        int option_id = 0;
        if (extrabed.equals("y") && (pension.equals("n"))) {
            option_id = 1;
        }
        else if (extrabed.equals("y") && (pension.equals("h"))) {
            option_id = 4;
        }
        else if(extrabed.equals("y") && (pension.equals("f"))) {
            option_id = 5;
        }
        else if (extrabed.equals("n") && (pension.equals("n"))) {
            option_id = 0;
        }
        else if(extrabed.equals("n") && (pension.equals("h"))) {
            option_id = 2;
        }
        else if(extrabed.equals("n") && (pension.equals("f"))) {
            option_id = 3;
        }
        else {
            System.out.println("Please input information properly");
            }

       try {
            statement = conn.prepareStatement("INSERT INTO bookings(book_id,customer_id, hotel_id, room_id, option_id," +
                    "number_of_people, check_in, check_out) VALUES (?,?,?,?,?,?,?,?)");

            statement.setInt(1, book_id);
            statement.setInt(2, customer_id);
            statement.setInt(3, hotel_id);
            statement.setInt(4, room_id);
            statement.setInt(5, option_id);
            statement.setInt(6, number_of_people);
            statement.setString(7, check_in);
            statement.setString(8, check_out);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
}
