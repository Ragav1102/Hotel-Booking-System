package user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import services.BookingService;
import entities.Booking;

public class BookingServiceUI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BookingService bookingService = new BookingService();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Booking Service Menu:");
            System.out.println("1. Add Booking");
            System.out.println("2. Update Booking");
            System.out.println("3. Delete Booking");
            System.out.println("4. Show All Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = getIntInput();
            switch (choice) {
                case 1:
                    addBooking();
                    break;
                case 2:
                    updateBooking();
                    break;
                case 3:
                    deleteBooking();
                    break;
                case 4:
                    showAllBookings();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void addBooking() {
        System.out.println("Enter Booking Details:");
        System.out.print("Booking ID: ");
        int bookingId = getIntInput();
        System.out.print("Guest ID: ");
        int guestId = getIntInput();
        System.out.print("Room ID: ");
        int roomId = getIntInput();
        System.out.print("Check-In Date (yyyy-MM-dd): ");
        java.util.Date checkInDate = getDateInput();
        System.out.print("Check-Out Date (yyyy-MM-dd): ");
        java.util.Date checkOutDate = getDateInput();
        Booking booking = new Booking(bookingId, guestId, roomId, checkInDate, checkOutDate);
        try {
            bookingService.addBooking(booking);
            System.out.println("Booking added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding booking: " + e.getMessage());
        }
    }

    private static void updateBooking() {
        System.out.print("Enter Booking ID to update: ");
        int bookingId = getIntInput();
        Booking booking = new Booking(bookingId, 0, 0, null, null); // Placeholder booking object, only ID is used for update
        System.out.println("Enter Updated Booking Details:");
        System.out.print("Guest ID: ");
        booking.setGuestId(getIntInput());
        System.out.print("Room ID: ");
        booking.setRoomId(getIntInput());
        System.out.print("Check-In Date (yyyy-MM-dd): ");
        booking.setCheckInDate(getDateInput());
        System.out.print("Check-Out Date (yyyy-MM-dd): ");
        booking.setCheckOutDate(getDateInput());
        try {
            bookingService.updateBooking(booking);
            System.out.println("Booking updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating booking: " + e.getMessage());
        }
    }

    private static void deleteBooking() {
        System.out.print("Enter Booking ID to delete: ");
        int bookingId = getIntInput();
        try {
            bookingService.deleteBooking(bookingId);
            System.out.println("Booking deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting booking: " + e.getMessage());
        }
    }

    private static void showAllBookings() {
        try {
            List<Booking> bookings = bookingService.getAllBookings();
            System.out.println("All Bookings:");
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving bookings: " + e.getMessage());
        }
    }

    private static int getIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine(); // Clear input buffer
            }
        }
    }

    private static java.util.Date getDateInput() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        while (true) {
            try {
                String dateStr = scanner.next();
                return dateFormat.parse(dateStr);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }
    }
}

