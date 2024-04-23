package user;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import services.RoomServices;
import entities.Room;

public class RoomServiceUI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final RoomServices roomServices = new RoomServices();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Room Service Menu:");
            System.out.println("1. Add Room");
            System.out.println("2. Update Room");
            System.out.println("3. Delete Room");
            System.out.println("4. Show All Rooms");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = getIntInput();
            switch (choice) {
                case 1:
                    addRoom();
                    break;
                case 2:
                    updateRoom();
                    break;
                case 3:
                    deleteRoom();
                    break;
                case 4:
                    showAllRooms();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
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

    private static int getIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }
        }
    }
}

