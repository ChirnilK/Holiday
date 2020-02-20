package com.company;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {

    SqlConsole sqlConsole = new SqlConsole();
    SearchingRoom searchingRoom = new SearchingRoom(sqlConsole.getConn());
    Registration registration = new Registration(sqlConsole.getConn());
    CancelingBook cancelingBook = new CancelingBook(sqlConsole.getConn());

    private Connection conn = null;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public void startProgram() throws IOException {
        boolean on = true;
        while(on) {
            startMenu();
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    String beach = "Beach";
                    start(beach);
                    break;

                case "2":
                    String urban = "Urban";
                    start(urban);
                    break;

                case "3":
                    System.out.println("See you!");
                    on = false;
                    break;

                default:
                    System.out.println("Enter 1 or 2");
                    break;
            }
        }
    }


    public void start(String purpose) throws IOException {
        boolean on = true;
        while (on) {
            adminMenu();
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    String registeredSocialnr = registration.registerCustomer();
                    resultSet = registration.searchBySocialSecnr(registeredSocialnr);
                    if (resultSet!= null) {
                        registration.printResult(resultSet);
                    }
                        break;

                case "2":
                    ArrayList result = searchingRoom.searchRoom(purpose);
                    int booking_id = searchingRoom.bookRoom(result);
                    resultSet = searchingRoom.searchByBookid(booking_id);
                    if (resultSet!= null) {
                        ArrayList forPrice = searchingRoom.bookingResult(resultSet);
                        searchingRoom.bookingTotalPrice(forPrice);
                    }
                        break;

                case "3":

                    resultSet = searchingRoom.searchByBookid(booking_id);


                default:
                    System.out.println("Enter a number between 1 to 5 or 11");
                    break;
            }
        }
    }

    private void startMenu() {
        System.out.println("");
        System.out.println("----------------------------------");
        System.out.println("           Skåne travel           ");
        System.out.println("----------------------------------");
        System.out.println("");
        System.out.println("What is the purpose of your trip ?");
        System.out.println("");
        System.out.println("  Beach holidays     : Enter '1'  ");
        System.out.println("    Urban trip       : Enter '2'  ");
        System.out.println("          Quit       : Enter '3'  ");
        System.out.println("----------------------------------");
    }

    private void adminMenu() {
        System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("Choose a number");
        System.out.println("1 : Registering customer");
        System.out.println("2 : Searching room & cancelingBook");
        System.out.println("3 : Canceling book");
        System.out.println("4 : Changing book");
        System.out.println("11: Quit");
        System.out.println("--------------------------------------");
    }
}
