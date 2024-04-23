package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database_connection.Database_Connection;
import entities.Booking;

public class BookingService {
    public void addBooking(Booking booking) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO booking (booking_id, guest_id, room_id, check_indate, check_outdate) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Database_Connection.getdbConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, booking.getBookingId());
            statement.setInt(2, booking.getGuestId());
            statement.setInt(3, booking.getRoomId());
            statement.setDate(4, new java.sql.Date(booking.getCheckInDate().getTime()));
            statement.setDate(5, new java.sql.Date(booking.getCheckOutDate().getTime()));

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateBooking(Booking booking) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE booking SET guest_id = ?, room_id = ?, check_indate = ?, check_outdate = ? WHERE booking_id = ?";
        try (Connection connection = Database_Connection.getdbConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, booking.getGuestId());
            statement.setInt(2, booking.getRoomId());
            statement.setDate(3, new java.sql.Date(booking.getCheckInDate().getTime()));
            statement.setDate(4, new java.sql.Date(booking.getCheckOutDate().getTime()));
            statement.setInt(5, booking.getBookingId());

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteBooking(int bookingId) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM booking WHERE booking_id = ?";
        try (Connection connection = Database_Connection.getdbConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookingId);

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Booking> getAllBookings() throws ClassNotFoundException, SQLException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM booking";
        try (Connection connection = Database_Connection.getdbConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int bookingId = resultSet.getInt("booking_id");
                int guestId = resultSet.getInt("guest_id");
                int roomId = resultSet.getInt("room_id");
                java.util.Date checkInDate = new java.util.Date(resultSet.getDate("check_indate").getTime());
                java.util.Date checkOutDate = new java.util.Date(resultSet.getDate("check_outdate").getTime());
                Booking booking = new Booking(bookingId, guestId, roomId, checkInDate, checkOutDate);
                bookings.add(booking);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return bookings;
    }
}

