package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database_connection.Database_Connection;
import entities.Payment;

public class PaymentService {
    public void addPayment(Payment payment) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO payment (payment_id, booking_id, payment_amount, payment_date, payment_method) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Database_Connection.getdbConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, payment.getPaymentId());
            statement.setInt(2, payment.getBookingId());
            statement.setDouble(3, payment.getPaymentAmount());
            statement.setDate(4, new java.sql.Date(payment.getPaymentDate().getTime()));
            statement.setString(5, payment.getPaymentMethod());

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updatePayment(Payment payment) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE payment SET booking_id = ?, payment_amount = ?, payment_date = ?, payment_method = ? WHERE payment_id = ?";
        try (Connection connection = Database_Connection.getdbConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, payment.getBookingId());
            statement.setDouble(2, payment.getPaymentAmount());
            statement.setDate(3, new java.sql.Date(payment.getPaymentDate().getTime()));
            statement.setString(4, payment.getPaymentMethod());
            statement.setInt(5, payment.getPaymentId());

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deletePayment(int paymentId) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM payment WHERE payment_id = ?";
        try (Connection connection = Database_Connection.getdbConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, paymentId);

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public List<Payment> getAllPayments() throws ClassNotFoundException, SQLException {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payment";
        try (Connection connection = Database_Connection.getdbConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int paymentId = resultSet.getInt("payment_id");
                int bookingId = resultSet.getInt("booking_id");
                double paymentAmount = resultSet.getDouble("payment_amount");
                java.util.Date paymentDate = new java.util.Date(resultSet.getDate("payment_date").getTime());
                String paymentMethod = resultSet.getString("payment_method");
                Payment payment = new Payment(paymentId, bookingId, paymentAmount, paymentDate, paymentMethod);
                payments.add(payment);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return payments;
    }
    
    
    
}