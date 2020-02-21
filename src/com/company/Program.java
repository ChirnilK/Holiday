package com.company;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {

    SqlConsole sqlConsole = new SqlConsole();
    SearchingRoom searchingRoom = new SearchingRoom(sqlConsole.getConn());
    Registration registration = new Registration(sqlConsole.getConn());
    CancellingRescheduling cancellingRescheduling = new CancellingRescheduling(sqlConsole.getConn());

    private Connection conn = null;
    private PreparedStatement statement;
    private ResultSet resultSet;


    public void start() throws IOException {
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
                    String purpose = startProgram();
                    ArrayList result = searchingRoom.searchRoom(purpose);
                    int booking_id = searchingRoom.bookRoom(result);
                    resultSet = searchingRoom.searchByBookid(booking_id);
                    if (resultSet!= null) {
                        ArrayList forPrice = searchingRoom.bookingResult(resultSet);
                        searchingRoom.bookingTotalPrice(forPrice);
                    }
                        break;

                case "3":
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Do you want to cancel your book? y/n");
                    String answer = scan.nextLine();
                    if (answer.equals("y")){
                        System.out.println("Input book-id");
                        int cancelBook = Integer.parseInt(scan.nextLine());
                        resultSet = searchingRoom.searchByBookid(cancelBook);
                        searchingRoom.bookingResult(resultSet);
                        System.out.println("");
                        System.out.println("Is this booking that you want to cancel? y/n");
                        String book = scan.nextLine();
                        if (book.equals("y")) {
                            cancellingRescheduling.deleteBook(cancelBook);
                            System.out.println("");
                            System.out.println("Your booking was now cancelled");
                            System.out.println("");
                            resultSet = cancellingRescheduling.selectBookings();
                            System.out.println("========== Bookings table ==========");
                            System.out.println("");
                            searchingRoom.bookingResult(resultSet);

                        }
                        else if(book.equals("n")) {
                            break;
                        }
                    }
                    else{
                        System.out.println("See you!");
                    }
                        break;

                case "4":
                    Scanner reschedule = new Scanner(System.in);
                    System.out.println("Do you want to reschedule your book? y/n");
                    String res = reschedule.nextLine();
                    if (res.equals("y")) {
                        System.out.println("Input book-id");
                        int changeBook = Integer.parseInt(reschedule.nextLine());
                        resultSet = searchingRoom.searchByBookid(changeBook);
                        ArrayList book_info = searchingRoom.bookingResult(resultSet);
                        System.out.println("");
                        System.out.println("Is this booking that you want to reschedule? y/n");
                        String change = reschedule.nextLine();
                        if (change.equals("y")) {
                            System.out.println("When is your new check-in date? Input ex; 2020-01-30");
                            String newCheckIn = reschedule.next();
                            LocalDate newCheckInDate = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(newCheckIn));
                            LocalDate startOfSeason = LocalDate.of(2020, 6, 1);
                            if (newCheckInDate.isBefore(startOfSeason)) {
                                System.out.println("Please call us later!");
                            } else {
                                System.out.println("When is your check-out date? Input ex; 2020-01-30");
                                String newCheckOut = reschedule.next();
                                LocalDate newCheckOutDate = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(newCheckOut));
                                LocalDate endOfSeason = LocalDate.of(2020, 7, 30);
                                if (newCheckOutDate.isAfter(endOfSeason)) {
                                    System.out.println("Sorry! See you next year!");
                                }
                                else{
                                    cancellingRescheduling.rescheduleCheck(book_info, newCheckIn, newCheckOut);
                                    System.out.println("Is this ok? y/n");
                                    Scanner resche = new Scanner(System.in);
                                    String lastCheck = resche.nextLine();
                                    if (lastCheck.equals("y")){
                                        cancellingRescheduling.reschedule(changeBook, newCheckIn, newCheckOut);
                                        resultSet = searchingRoom.searchByBookid(changeBook);
                                        searchingRoom.bookingResult(resultSet);
                                        break;
                                    }
                                    else if(lastCheck.equals("n")){
                                        System.out.println("Please contact us again.");
                                        break;
                                    }
                                    else{
                                        break;
                                    }
                                }
                            }
                        }
                        else{
                            System.out.println("See you!");
                        }
                    }

                default:
                    break;
            }
        }
    }

    private String startProgram() throws IOException {
        boolean on = true;
        String purpose = "";
        while (on) {
            searchMenu();
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    String beach = "Beach";
                    purpose = beach;
                    break;

                case "2":
                    String urban = "Urban";
                    purpose = urban;
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
        return purpose;
    }



    private void searchMenu() {
        System.out.println("");
        System.out.println("----------------------------------");
        System.out.println("What is the purpose of your trip ?");
        System.out.println("");
        System.out.println("  Beach holidays     : Enter '1'  ");
        System.out.println("   Urban trip        : Enter '2'  ");
        System.out.println("      Quit           : Enter '3'  ");
        System.out.println("----------------------------------");
    }

    private void adminMenu() {
        System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("           Skåne travel               ");
        System.out.println("--------------------------------------");
        System.out.println("Choose a number");
        System.out.println("1 : Registering customer");
        System.out.println("2 : Searching & Booking room");
        System.out.println("3 : Cancellation of book");
        System.out.println("4 : Rescheduling book");
        System.out.println("11: Quit");
        System.out.println("--------------------------------------");
    }
}
