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

    public ArrayList<Integer> searchRoom(String purpose) {
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
                    System.out.println("Do you want to use pool in your hotel? y/n");
                    int pool = 0;
                    String poolAnswer = scanner.nextLine();
                    if (poolAnswer.equals("y")){
                        pool = 1;
                    }
                    System.out.println("Do you want to have evening entertainment? y/n");
                    int evening_entertainment = 0;
                    String entertainment = scanner.nextLine();
                    if (entertainment.equals("y")){
                        evening_entertainment = 1;
                    }
                    System.out.println("Do you want to use kids club? y/n");
                    int kids_club = 0;
                    String kids = scanner.nextLine();
                    if (kids.equals("y")){
                        kids_club = 1;
                    }
                    System.out.println("Do you want to have restaurant in your hotel? y/n");
                    int restaurant = 0;
                    String eat = scanner.nextLine();
                    if (eat.equals("y")){
                        restaurant = 1;
                    }

                    try {
                        statement = conn.prepareStatement("select hotelroom_id, hotel_name from all_room_list " + "\n" +
                                "where hotelroom_id not in(select hotelroom_id from booked_list " + "\n" +
                                "where not (check_in >= ? OR check_out <= ?) AND room_id = ?)");

                        System.out.println(checkInDate);
                        System.out.println(checkOutDate);
                        statement.setString(1, checkOut);
                        statement.setString(2, checkIn);
                        statement.setInt(3, room_id);
                        statement.setInt(4, pool);
                        statement.setInt(5, evening_entertainment);
                        statement.setInt(6, kids_club);
                        statement.setInt(7, restaurant);

                        resultSet = statement.executeQuery();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    String row = "";
                    ArrayList<Integer> result = new ArrayList<>();

                    try {
                        System.out.println("You can book this/these hotel(s) between " + checkInDate + " and " + checkOutDate);
                        while (resultSet.next()) {
                            result.add(resultSet.getInt("hotelroom_id"));
                            row = "Hotelroom id: " + resultSet.getInt("hotelroom_id")
                                    + ", Hotel name: " + resultSet.getString("hotel_name");
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

            public void findOne(ArrayList result) {

                System.out.println(result);
            }
    }
