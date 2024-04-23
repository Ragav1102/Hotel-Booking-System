package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database_connection.Database_Connection;
import entities.Guest;

public class GuestService {
    public void addGuest(Guest guest) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO guest (guest_id, first_name, last_name, email, phone_number) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Database_Connection.getdbConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, guest.getGuestId());
            statement.setString(2, guest.getFirstName());
            statement.setString(3, guest.getLastName());
            statement.setString(4, guest.getEmail());
            statement.setString(5, guest.getPhoneNumber());

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateGuest(Guest guest) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE guest SET first_name = ?, last_name = ?, email = ?, phone_number = ? WHERE guest_id = ?";
        try (Connection connection = Database_Connection.getdbConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, guest.getFirstName());
            statement.setString(2, guest.getLastName());
            statement.setString(3, guest.getEmail());
            statement.setString(4, guest.getPhoneNumber());
            statement.setInt(5, guest.getGuestId());

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteGuest(int guestId) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM guest WHERE guest_id = ?";
        try (Connection connection = Database_Connection.getdbConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, guestId);

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Guest> getAllGuests() throws ClassNotFoundException, SQLException {
        List<Guest> guests = new ArrayList<>();
        String sql = "SELECT * FROM guest";
        try (Connection connection = Database_Connection.getdbConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int guestId = resultSet.getInt("guest_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                Guest guest = new Guest(guestId, firstName, lastName, email, phoneNumber);
                guests.add(guest);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return guests;
    }
}

