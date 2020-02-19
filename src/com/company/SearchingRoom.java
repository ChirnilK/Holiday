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

    public ArrayList<Integer> searchRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== What kind of room are you looking for ===");
        System.out.println("How many are you? Select a number");
        int room_id = 0;
        int numberOfPeople = Integer.parseInt(scanner.nextLine());
        if (numberOfPeople == 1) {
            System.out.println("Is Single room fine? y/n");
            String single = scanner.nextLine();
            if (single.equals("y")) {
                room_id = 1;
            } else {
                System.out.println("Do you want to stay in Double room? y/n");
                String doub = scanner.nextLine();
                if (doub.equals("y")) {
                    room_id = 2;
                } else {
                    System.out.println("Do you want to stay in Twin room? y/n");
                    String twin = scanner.nextLine();
                    if (twin.equals("y")) {
                        room_id = 3;
                    } else {
                        System.out.println("Do you want to stay in family room? y/n");
                        String fam = scanner.nextLine();
                        if (fam.equals("y")) {
                            room_id = 4;
                        } else {
                            System.out.println("Do you want to stay in Executive suite room? y/n");
                            String sweet = scanner.nextLine();
                            if (sweet.equals("y")) {
                                room_id = 5;
                            } else {
                                System.out.println("Please call again");
                            }
                        }

                    }

                }
            }
        } else if (numberOfPeople == 2) {
            System.out.println("Is Double room fine? y/n");
            String dou = scanner.nextLine();
            if (dou.equals("y")) {
                room_id = 2;
            } else {
                System.out.println("Do you want to stay in Twin room? y/n");
                String twin = scanner.nextLine();
                if (twin.equals("y")) {
                    room_id = 3;
                } else {
                    System.out.println("Do you want to stay in family room? y/n");
                    String fam = scanner.nextLine();
                    if (fam.equals("y")) {
                        room_id = 4;
                    } else {
                        System.out.println("Do you want to stay in Executive suite room? y/n");
                        String sweet = scanner.nextLine();
                        if (sweet.equals("y")) {
                            room_id = 5;
                        } else {
                            System.out.println("Please call again");
                        }
                    }
                }
            }
        } else if (numberOfPeople == 3) {
            System.out.println("Do you want to stay in Twin room with extra bed? y/n");
            String twin = scanner.nextLine();
            if (twin.equals("y")) {
                room_id = 3;
            } else {
                System.out.println("Do you want to stay in family room? y/n");
                String fam = scanner.nextLine();
                if (fam.equals("y")) {
                    room_id = 4;
                } else {
                    System.out.println("Do you want to stay in Executive suite room? y/n");
                    String sweet = scanner.nextLine();
                    if (sweet.equals("y")) {
                        room_id = 5;
                    } else {
                        System.out.println("Please call again");
                    }
                }
            }
        } else if (numberOfPeople == 4) {
            System.out.println("Do you want to stay in family room? y/n");
            String fam = scanner.nextLine();
            if (fam.equals("y")) {
                room_id = 4;
            } else {
                System.out.println("Sorry we can book only one room just now. Please come back after summer.");
            }
        }


        System.out.println("When is your check in date? Input ex; 2020-01-30");
        Date checkInDate = Date.valueOf(scanner.next());
        System.out.println("When is your check out date? Input ex; 2020-01-30");
        Date checkOutDate = Date.valueOf(scanner.next());

        try {
            statement = conn.prepareStatement("select hotelroom_id, hotel_name from all_room_list " +"\n"+
                    "where hotelroom_id not in(select hotelroom_id from booked_list " +"\n"+
                    "where not (check_in >= ? OR check_out <= ?) AND room_id = ?)");

            System.out.println(checkInDate);
            System.out.println(checkOutDate);
            statement.setDate(1, checkOutDate);
            statement.setDate(2, checkInDate);
            statement.setInt(3, room_id);
            resultSet = statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String row = "";
        ArrayList<Integer> result = new ArrayList<>();

        try{
            System.out.println("You can book this/these hotel(s) between " +checkInDate+ " and " + checkOutDate);
            while (resultSet.next()) {
                result.add(resultSet.getInt("hotelroom_id"));
                row = "Hotelroom id: " + resultSet.getInt("hotelroom_id")
                        + ", Hotel name: " + resultSet.getString("hotel_name");
                System.out.println(row);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public void findOne(ArrayList result) {

        System.out.println(result);
    }
}
