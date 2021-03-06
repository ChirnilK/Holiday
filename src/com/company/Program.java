package com.company;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {

    private SqlConsole sqlConsole = new SqlConsole();
    private SearchingRoom searchingRoom = new SearchingRoom(sqlConsole.getConn());
    private Registration registration = new Registration(sqlConsole.getConn());
    private CancellingRescheduling cancellingRescheduling = new CancellingRescheduling(sqlConsole.getConn());

    private ResultSet resultSet;


    public void start(){
        boolean on = true;
        while (on) {
            adminMenu();
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":                 //1 : Registering customer
                    String registeredSocialnr = registration.registerCustomer();      //insert info of new customer. return social secnr
                    resultSet = registration.searchCustomerBySocialSecnr(registeredSocialnr);   //select info by social secnr
                    registration.customerPrintResult(resultSet);                            //show new customer's info
                    break;

                case "2":                 //2 : Searching & Booking room
                    String purpose = startProgram();                                 //choose beach or city
                    ArrayList answers = searchingRoom.questionsForSearchRoom(purpose);      //questions to get a available hotel list
                    ArrayList result = searchingRoom.selectForRooms(answers);      //get a list of hotels after filtering.
                    if (result==null){
                        break;
                    }
                    else{
                        int booking_id = searchingRoom.bookRoom(result);              //choose one hotelroom from the previous list and book. return book_id
                        resultSet = searchingRoom.searchByBookid(booking_id);        //using book_id, select the booking information
                    }

                    if(resultSet==null){
                        break;
                    }
                    else{
                        ArrayList forPrice = searchingRoom.bookingResult(resultSet);   //booking info
                        searchingRoom.bookingTotalPrice(forPrice);                    /// get total price for staying
                        System.out.println("Thank you!");
                        System.out.println("Have a great trip!");
                    }
                    break;

                case "3":                       //3 : Cancellation of book
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Input book-id that you want to cancel");
                    System.out.println("== Important == Please input correct book-id");
                    try {
                        int cancelBook = Integer.parseInt(scan.nextLine());                           //Input book_id
                        resultSet = searchingRoom.searchByBookid(cancelBook);                         //SQL select... return the booking
                        ArrayList cancel = searchingRoom.bookingResult(resultSet);                    //show booking info
                        if (cancel != null) {
                            System.out.println("");
                            System.out.println("Is this booking that you want to cancel? y/n");
                            String book = scan.nextLine();
                            if (book.equals("y")) {
                                cancellingRescheduling.deleteBook(cancelBook);                 //delete booking
                                System.out.println("");
                                System.out.println("Your booking has been successfully cancelled");
                                System.out.println("");
                                resultSet = cancellingRescheduling.selectBookings();           //show all bookings
                                System.out.println("========== Bookings table ==========");
                                System.out.println("");
                                searchingRoom.bookingResult(resultSet);
                            } else {
                                System.out.println("See you!");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Please input correct information");
                        e.printStackTrace();
                    }

                    break;

                case "4":                               //4 : Rescheduling book
                    Scanner reschedule = new Scanner(System.in);
                    System.out.println("Here we can change only your booking dates.");
                    System.out.println("If you wish other changing, please cancel your booking first,");
                    System.out.println("then, rebook your hotel.");
                    System.out.println("Do you want to reschedule your book? y/n");
                    String res = reschedule.nextLine();
                    if (res.equals("y")) {
                        System.out.println("Input book-id");                              //input book_id
                        try {
                            int changeBook = Integer.parseInt(reschedule.nextLine());
                            resultSet = searchingRoom.searchByBookid(changeBook);        //search book info
                            ArrayList book_info = searchingRoom.bookingResult(resultSet);  // show book info
                            searchingRoom.bookingTotalPrice(book_info);                    // show total price
                            System.out.println("");
                            System.out.println("Is this booking that you want to reschedule? y/n");
                            String change = reschedule.nextLine();
                            if (change.equals("y")) {
                                System.out.println("When is your new check-in date? Input ex; 2020-01-30");  //asking new date
                                String newCheckIn = reschedule.next();
                                LocalDate newCheckInDate = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(newCheckIn));
                                LocalDate startOfSeason = LocalDate.of(2020, 6, 1);
                                if (newCheckInDate.isBefore(startOfSeason)) {
                                    System.out.println("The campaign season is between 2020-06-01 to 2020-07-30!");
                                } else {
                                    System.out.println("When is your check-out date? Input ex; 2020-01-30");
                                    String newCheckOut = reschedule.next();
                                    LocalDate newCheckOutDate = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(newCheckOut));
                                    LocalDate endOfSeason = LocalDate.of(2020, 7, 30);
                                    if (newCheckOutDate.isAfter(endOfSeason)) {
                                        System.out.println("The campaign season is between 2020-06-01 to 2020-07-30!");
                                    } else {
                                        cancellingRescheduling.rescheduleCheck(book_info, newCheckIn, newCheckOut);  // for reschedule
                                        System.out.println("Is this ok? y/n");
                                        Scanner resche = new Scanner(System.in);
                                        String lastCheck = resche.nextLine();
                                        if (lastCheck.equals("y")) {
                                            cancellingRescheduling.reschedule(changeBook, newCheckIn, newCheckOut);   //update(reschedule) booking
                                            resultSet = searchingRoom.searchByBookid(changeBook);              //search rescheduled booking
                                            ArrayList rebook = searchingRoom.bookingResult(resultSet);        //show rescheduled booking
                                            searchingRoom.bookingTotalPrice(rebook);                         //show new price of rescheduled booking
                                            System.out.println("Thank you!");
                                            System.out.println("Have a great trip!");
                                            break;
                                        } else if (lastCheck.equals("n")) {
                                            System.out.println("Call us again if any!");
                                            break;
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            } else {
                                System.out.println("See you!");
                            }
                        } catch (Exception e) {
                            System.out.println("Input correct information");
                        }
                    } else {
                        System.out.println("See you!");
                    } break;



                case "9":                            //9: Quit
                    on = false;
                    System.exit(0);
                    break;

                default:
                    System.out.println("Select a number 1-4 or 9 to quit");
                    break;
            }
        }
    }

    private String startProgram(){
        boolean login = true;
        String purpose = "";
        while (login) {
            searchMenu();
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":                             //1: Beach holidays
                    String beach = "Beach";
                    purpose = beach;
                    break;

                case "2":
                    String urban = "Urban";           //2: Urban trip
                    purpose = urban;
                    break;

                default:
                    System.out.println("Enter 1 or 2");
                    break;
                }
            return purpose;
        }
        return null;
    }





    private void searchMenu() {
        System.out.println("");
        System.out.println("----------------------------------");
        System.out.println("What is the purpose of your trip ?");
        System.out.println("");
        System.out.println("  Beach holidays     : Enter '1'  ");
        System.out.println("   Urban trip        : Enter '2'  ");
        System.out.println("----------------------------------");
    }

    private void adminMenu() {
        System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("       Skåne travel system            ");
        System.out.println("--------------------------------------");
        System.out.println("Choose a number");
        System.out.println("1 : Registering customer");
        System.out.println("2 : Searching & Booking room");
        System.out.println("3 : Cancellation of book");
        System.out.println("4 : Rescheduling book");
        System.out.println("9 : Quit");
        System.out.println("--------------------------------------");
    }
}
