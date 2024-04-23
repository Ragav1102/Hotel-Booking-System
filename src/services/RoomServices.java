package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database_connection.Database_Connection;
import entities.Room;

public class RoomServices {
               public void addRoom(Room room) throws ClassNotFoundException, SQLException {
	        String sql = "INSERT INTO room (room_id, room_number, room_type, is_available) VALUES (?, ?, ?, ?)";
	        try (Connection connection = Database_Connection.getdbConnection();
	             PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, room.getRoomId());
	            statement.setString(2, room.getRoomNumber());
	            statement.setString(3, room.getRoomType());
	            statement.setBoolean(4, room.isAvailable());

	            statement.executeUpdate();
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }

	    public void updateRoom(Room room) throws ClassNotFoundException, SQLException {
	        String sql = "UPDATE room SET room_number = ?, room_type = ?, is_available = ? WHERE room_id = ?";
	        try (Connection connection = Database_Connection.getdbConnection();
	             PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, room.getRoomNumber());
	            statement.setString(2, room.getRoomType());
	            statement.setBoolean(3, room.isAvailable());
	            statement.setInt(4, room.getRoomId());

	            statement.executeUpdate();
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }

	    public boolean deleteRoom(int roomId) throws ClassNotFoundException, SQLException {
	        String sql = "DELETE FROM room WHERE room_id = ?";
	        try (Connection connection = Database_Connection.getdbConnection();
	             PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, roomId);

	            statement.executeUpdate();
	            return true;
	        } catch (Exception e) {
	            System.out.println(e);
	        }
			return false;
	    }

	    public List<Room> getAllRooms() throws ClassNotFoundException, SQLException {
	        List<Room> rooms = new ArrayList<>();
	        String sql = "SELECT * FROM room";
	        try (Connection connection = Database_Connection.getdbConnection();
	             PreparedStatement statement = connection.prepareStatement(sql);
	             ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                int roomId = resultSet.getInt("room_id");
	                String roomNumber = resultSet.getString("room_number");
	                String roomType = resultSet.getString("room_type");
	                boolean isAvailable = resultSet.getBoolean("is_available");
	                Room room = new Room(roomId, roomNumber, roomType, isAvailable);
	                rooms.add(room);
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        return rooms;
	    }
	    }
