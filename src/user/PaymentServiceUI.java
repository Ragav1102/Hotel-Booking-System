package user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import services.PaymentService;
import entities.Payment;

public class PaymentServiceUI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PaymentService paymentService = new PaymentService();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Payment Service Menu:");
            System.out.println("1. Add Payment");
            System.out.println("2. Update Payment");
            System.out.println("3. Delete Payment");
            System.out.println("4. Show All Payments");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = getIntInput();
            switch (choice) {
                case 1:
                    addPayment();
                    break;
                case 2:
                    updatePayment();
                    break;
                case 3:
                    deletePayment();
                    break;
                case 4:
                    showAllPayments();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
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

