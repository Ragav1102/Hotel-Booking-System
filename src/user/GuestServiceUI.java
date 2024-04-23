package user;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import services.GuestService;
import entities.Guest;

public class GuestServiceUI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GuestService guestService = new GuestService();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Guest Service Menu:");
            System.out.println("1. Add Guest");
            System.out.println("2. Update Guest");
            System.out.println("3. Delete Guest");
            System.out.println("4. Show All Guests");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = getIntInput();
            switch (choice) {
                case 1:
                    addGuest();
                    break;
                case 2:
                    updateGuest();
                    break;
                case 3:
                    deleteGuest();
                    break;
                case 4:
                    showAllGuests();
                    break;
                case 5:
                	
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void addGuest() {
        System.out.println("Enter Guest Details:");
        System.out.print("Guest ID: ");
        int guestId = getIntInput();
        System.out.print("First Name: ");
        String firstName = scanner.next();
        System.out.print("Last Name: ");
        String lastName = scanner.next();
        System.out.print("Email: ");
        String email = scanner.next();
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.next();
        Guest guest = new Guest(guestId, firstName, lastName, email, phoneNumber);
        try {
            guestService.addGuest(guest);
            System.out.println("Guest added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding guest: " + e.getMessage());
        }
    }

    private static void updateGuest() {
        System.out.print("Enter Guest ID to update: ");
        int guestId = getIntInput();
        Guest guest = new Guest(guestId, "", "", "", ""); // Placeholder guest object, only ID is used for update
        System.out.println("Enter Updated Guest Details:");
        System.out.print("First Name: ");
        guest.setFirstName(scanner.next());
        System.out.print("Last Name: ");
        guest.setLastName(scanner.next());
        System.out.print("Email: ");
        guest.setEmail(scanner.next());
        System.out.print("Phone Number: ");
        guest.setPhoneNumber(scanner.next());
        try {
            guestService.updateGuest(guest);
            System.out.println("Guest updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating guest: " + e.getMessage());
        }
    }

    private static void deleteGuest() {
        System.out.print("Enter Guest ID to delete: ");
        int guestId = getIntInput();
        try {
            guestService.deleteGuest(guestId);
            System.out.println("Guest deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting guest: " + e.getMessage());
        }
    }

    private static void showAllGuests() {
        try {
            List<Guest> guests = guestService.getAllGuests();
            System.out.println("All Guests:");
            for (Guest guest : guests) {
                System.out.println(guest);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving guests: " + e.getMessage());
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
}

