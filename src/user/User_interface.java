package user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entities.Booking;
import entities.Guest;
import entities.Payment;
import entities.Room;
import services.BookingService;
import services.GuestService;
import services.PaymentService;
import services.RoomServices;

public class User_interface {
	private static final Scanner scanner = new Scanner(System.in);
    private static final BookingService bookingService = new BookingService();
    private static final RoomServices roomServices = new RoomServices();
    private static final PaymentService paymentService = new PaymentService();
    private static final GuestService guestService = new GuestService();

    
    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Booking Service Menu:");
            System.out.println("1. Add Booking");
            System.out.println("2. Update Booking");
            System.out.println("3. Delete Booking");
            System.out.println("4. Show All Bookings");
            System.out.println("Guest Service Menu:");
            System.out.println("5. Add Guest");
            System.out.println("6. Update Guest");
            System.out.println("7. Delete Guest");
            System.out.println("8. Show All Guests");
            System.out.println("Room Service Menu:");
            System.out.println("9. Add Room");
            System.out.println("10. Update Room");
            System.out.println("11. Delete Room");
            System.out.println("12. Show All Rooms");
            System.out.println("Payment Service Menu:");
            System.out.println("13. Add Payment");
            System.out.println("14. Update Payment");
            System.out.println("15. Delete Payment");
            System.out.println("16. Show All Payments");
            System.out.println("17.Exit");          
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
                    addGuest();
                    break;
                case 6:
                    updateGuest();
                    break;
                case 7:
                    deleteGuest();
                    break;
                case 8:
                    showAllGuests();
                    break;
                case 9:
                    addRoom();
                    break;
                case 10:
                    updateRoom();
                    break;
                case 11:
                    deleteRoom();
                    break;
                case 12:
                    showAllRooms();
                    break;
                case 13:
                    addPayment();
                    break;
                case 14:
                    updatePayment();
                    break;
                case 15:
                    deletePayment();
                    break;
                case 16:
                    showAllPayments();
                    break;
              
                case 17:
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


    private static void addRoom() {
        System.out.println("Enter Room Details:");
        System.out.print("Room ID: ");
        int roomId = getIntInput();
        System.out.print("Room Number: ");
        String roomNumber = scanner.next();
        scanner.nextLine();
        System.out.print("Room Type: ");
        String roomType = scanner.next();
        System.out.print("Is Available (true/false): ");
        boolean isAvailable = scanner.nextBoolean();
        Room room = new Room(roomId, roomNumber, roomType, isAvailable);
        try {
            roomServices.addRoom(room);
            System.out.println("Room added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding room: " + e.getMessage());
        }
    }

    private static void updateRoom() {
        System.out.print("Enter Room ID to update: ");
        int roomId = getIntInput();
        Room room = new Room(roomId, "", "", false); // Placeholder room object, only ID is used for update
        System.out.println("Enter Updated Room Details:");
        System.out.print("Room Number: ");
        room.setRoomNumber(scanner.next());
        System.out.print("Room Type: ");
        room.setRoomType(scanner.next());
        System.out.print("Is Available (true/false): ");
        room.setAvailable(scanner.nextBoolean());
        try {
            roomServices.updateRoom(room);
            System.out.println("Room updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating room: " + e.getMessage());
        }
    }

    private static void deleteRoom() {
        System.out.print("Enter Room ID to delete: ");
        int roomId = getIntInput();
        try {
            if (roomServices.deleteRoom(roomId)) {
                System.out.println("Room deleted successfully.");
            } else {
                System.out.println("Room not found.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting room: " + e.getMessage());
        }
    }

    private static void showAllRooms() {
        try {
            List<Room> rooms = roomServices.getAllRooms();
            System.out.println("All Rooms:");
            for (Room room : rooms) {
                System.out.println(room);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving rooms: " + e.getMessage());
        }
    }

    private static void addPayment() {
        System.out.println("Enter Payment Details:");
        System.out.print("Payment ID: ");
        int paymentId = getIntInput();
        System.out.print("Booking ID: ");
        int bookingId = getIntInput();
        System.out.print("Payment Amount: ");
        double paymentAmount = scanner.nextDouble();
        System.out.print("Payment Date (yyyy-MM-dd): ");
        java.util.Date paymentDate = getDateInput();
        System.out.print("Payment Method: ");
        String paymentMethod = scanner.next();
        Payment payment = new Payment(paymentId, bookingId, paymentAmount, paymentDate, paymentMethod);
        try {
            paymentService.addPayment(payment);
            System.out.println("Payment added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding payment: " + e.getMessage());
        }
    }

    private static void updatePayment() {
        System.out.print("Enter Payment ID to update: ");
        int paymentId = getIntInput();
        Payment payment = new Payment(paymentId, 0, 0, null, ""); // Placeholder payment object, only ID is used for update
        System.out.println("Enter Updated Payment Details:");
        System.out.print("Booking ID: ");
        payment.setBookingId(getIntInput());
        System.out.print("Payment Amount: ");
        payment.setPaymentAmount(scanner.nextDouble());
        System.out.print("Payment Date (yyyy-MM-dd): ");
        payment.setPaymentDate(getDateInput());
        System.out.print("Payment Method: ");
        payment.setPaymentMethod(scanner.next());
        try {
            paymentService.updatePayment(payment);
            System.out.println("Payment updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating payment: " + e.getMessage());
        }
    }

    private static void deletePayment() {
        System.out.print("Enter Payment ID to delete: ");
        int paymentId = getIntInput();
        try {
            paymentService.deletePayment(paymentId);
            System.out.println("Payment deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting payment: " + e.getMessage());
        }
    }

    private static void showAllPayments() {
        try {
            List<Payment> payments = paymentService.getAllPayments();
            System.out.println("All Payments:");
            for (Payment payment : payments) {
                System.out.println(payment);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving payments: " + e.getMessage());
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
