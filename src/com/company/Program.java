package com.company;
import java.sql.*;
import java.util.Scanner;

public class Program {

    SqlConsole sqlConsole = new SqlConsole();
    SearchingRoom searchingRoom = new SearchingRoom(sqlConsole.getConn());
    Registration registration = new Registration(sqlConsole.getConn());

    private Connection conn = null;
    private PreparedStatement statement;
    private ResultSet resultSet;


    public void start() {
        boolean on = true;
        while (on) {
            adminMenu();
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    String registeredSocialnr = registration.registerCustomer();
                    resultSet = registration.searchBySocialSecnr(registeredSocialnr);
                    registration.printResult(resultSet);
                    break;

                case "2":
                    String result = searchingRoom.searchRoom();


                    break;

                default:
                    System.out.println("Enter a number between 1 to 5 or 11");
                    break;
            }
        }
    }


    private void adminMenu() {
        System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("Input a number");
        System.out.println("1 : Registering customer");
        System.out.println("2 : Searching room");
        System.out.println("3 : Booking");
        System.out.println("4 : Canceling book");
        System.out.println("5 : Changing book");
        System.out.println("11: Quit");
        System.out.println("--------------------------------------");
    }
}
