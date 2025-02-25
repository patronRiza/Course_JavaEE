package ru.prj;

import ru.prj.entity.Devices;
import ru.prj.entity.Request;
import ru.prj.entity.User;
import ru.prj.repositories.DeviceRepository;
import ru.prj.repositories.RequestRepository;
import ru.prj.repositories.UserRepository;
import ru.prj.repositories.impl.DeviceRepositoryImpl;
import ru.prj.repositories.impl.RequestRepositoryImpl;
import ru.prj.repositories.impl.UserRepositoryImpl;

import java.time.LocalDateTime;
import java.util.Scanner;

public class SecondAttest {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserRepository USER_REPOSITORY = new UserRepositoryImpl();
    private static final DeviceRepository DEVICE_REPOSITORY = new DeviceRepositoryImpl();
    private static final RequestRepository REQUEST_REPOSITORY = new RequestRepositoryImpl();

    public static void main(String[] args) {
        int continueProgramm = 1;

        while (continueProgramm == 1) {
            System.out.println("Please choose with which table you would like to search:\n");
            System.out.println("1. Devices\n2. Requests\n3. Users\n4. Exit");

            int choice = getValidChoice();
            switch (choice) {
                case 1:
                    takeInformFromDevices();
                    break;
                case 2:
                    takeInformFromRequests();
                    break;
                case 3:
                    takeInformFromUsers();
                    break;
                case 4:
                    continueProgramm = 0;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static int getValidChoice() {
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.println("Invalid input. Please enter a number:");
            input = scanner.nextLine().trim();
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return getValidChoice();
        }
    }

    private static void takeInformFromDevices() {
        System.out.println("Please enter the number of requests you would like to see:");
        System.out.println("1. Create new device.\n2. Find device by id.\n3. Get all devices.\n4. Update device.\n5. Delete device by id.\n6. Delete all devices.");
        int choice = getValidChoice();
        switch (choice) {
            case 1:
                Devices currentDevice = createDevice();
                DEVICE_REPOSITORY.save(currentDevice);
                break;
            case 2:
                System.out.println("Please enter the id you would like to see:");
                Long id = Long.parseLong(scanner.nextLine().trim());
                try {
                    System.out.println(DEVICE_REPOSITORY.findById(id));
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                var allDevice = DEVICE_REPOSITORY.getAll();
                System.out.println(allDevice);
                break;
            case 4:
                System.out.println("Please enter the id you would like to update:");
                Long deviceId = Long.parseLong(scanner.nextLine().trim());
                System.out.println("Please enter which user id you want to change to:");
                Long userId = Long.parseLong(scanner.nextLine().trim());
                System.out.println("Please enter status(true/false):");
                Boolean status = Boolean.parseBoolean(scanner.nextLine().trim());
                DEVICE_REPOSITORY.update(deviceId, userId, status);
                break;
            case 5:
                System.out.println("Please enter the id you would like to delete: ");
                Long currentId = Long.valueOf(scanner.nextLine().trim());
                DEVICE_REPOSITORY.deleteById(currentId);
                break;
            case 6:
                DEVICE_REPOSITORY.deleteAll();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private static Devices createDevice() {
        System.out.println("Please enter the user id of the device:");
        Long userId = Long.valueOf(scanner.nextLine().trim());
        System.out.println("Please enter the type of the device:");
        String type = scanner.nextLine();
        System.out.println("Please enter the model of the device:");
        String model = scanner.nextLine();
        System.out.println("Please enter the serial number of the device:");
        String serialNumber = scanner.nextLine();
        System.out.println("Please enter the warranty status (true/false) of the device:");
        Boolean warrantyStatus = Boolean.valueOf(scanner.nextLine().trim());

        return new Devices(1L, userId, type, model, serialNumber, warrantyStatus, LocalDateTime.now());
    }

    private static void takeInformFromRequests() {
        System.out.println("Please enter the number of requests you would like to see:");
        System.out.println("1. Create new request.\n2. Find request by id.\n3. Get all requests.\n4. Update request.\n5. Delete request by id.\n6. Delete all requests.\n7. Get count of types of requests.");
        int choice = getValidChoice();
        switch (choice) {
            case 1:
                Request newRequest = createRequest();
                REQUEST_REPOSITORY.save(newRequest);
                break;
            case 2:
                System.out.println("Please enter the id you would like to see:");
                Long id = Long.parseLong(scanner.nextLine().trim());
                try {
                    System.out.println(REQUEST_REPOSITORY.findById(id));
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                var allRequests = REQUEST_REPOSITORY.getAll();
                System.out.println(allRequests);
                break;
            case 4:
                System.out.println("Please enter the id you would like to update:");
                Long requestId = Long.parseLong(scanner.nextLine().trim());
                System.out.println("Please enter which assigned you want to change to:");
                Long userId = Long.parseLong(scanner.nextLine().trim());
                System.out.println("Please enter status:");
                String issue = scanner.nextLine();
                REQUEST_REPOSITORY.update(requestId, userId, issue, LocalDateTime.now());
                break;
            case 5:
                System.out.println("Please enter the id you would like to delete:");
                Long delId = Long.valueOf(scanner.nextLine().trim());
                REQUEST_REPOSITORY.deleteById(delId);
                break;
            case 6:
                REQUEST_REPOSITORY.deleteAll();
                break;
            case 7:
                var getTypeDevice = REQUEST_REPOSITORY.getTypeCounts();
                System.out.println(getTypeDevice);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private static Request createRequest() {
        System.out.println("Please enter the user id of the request:");
        Long userId = Long.valueOf(scanner.nextLine().trim());
        System.out.println("Please enter the type of the device:");
        String type = scanner.nextLine();
        System.out.println("Please enter the issue description of the request:");
        String issue = scanner.nextLine();
        System.out.println("Please enter the status('new', 'in_progress' or 'completed') of the request:");
        String status = scanner.nextLine();
        System.out.println("Please enter the assigned of the request:");
        Long assignedTo = Long.valueOf(scanner.nextLine().trim());

        return new Request(1L, userId, type, issue, status, assignedTo, LocalDateTime.now(), LocalDateTime.now());
    }

    private static void takeInformFromUsers() {
        System.out.println("Please enter the number of requests you would like to see:");
        System.out.println("1. Create new user.\n2. Find user by email.\n3. Get all users.\n4. Update user.\n5. Delete user by id.\n6. Delete all users.");
        int choice = getValidChoice();
        switch (choice) {
            case 1:
                User newUser = createUser();
                USER_REPOSITORY.create(newUser);
                break;
            case 2:
                System.out.println("Please enter the email you would like to see:");
                String findEmail = scanner.nextLine().trim();
                try {
                    System.out.println(USER_REPOSITORY.findByEmail(findEmail));
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                var getAllUsers = USER_REPOSITORY.getAll();
                System.out.println(getAllUsers);
                break;
            case 4:
                System.out.println("Please enter the id you would like to update:");
                Long requestId = Long.parseLong(scanner.nextLine().trim());
                System.out.println("Please enter the name you would like to update:");
                String name = scanner.nextLine().trim();
                System.out.println("Please enter the email you would like to update:");
                String email = scanner.nextLine().trim();
                System.out.println("Please enter the password you would like to update:");
                String password = scanner.nextLine().trim();
                System.out.println("Please enter the phone number you would like to update:");
                String phoneNumber = scanner.nextLine().trim();
                USER_REPOSITORY.update(requestId, name, email, password, phoneNumber);
                break;
            case 5:
                System.out.println("Please enter the id you would like to delete:");
                Long delId = Long.valueOf(scanner.nextLine().trim());
                USER_REPOSITORY.deleteById(delId);
                break;
            case 6:
                USER_REPOSITORY.deleteAll();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private static User createUser() {
        System.out.println("Please enter the name of the user:");
        String name = scanner.nextLine().trim();
        System.out.println("Please enter the email of the user:");
        String email = scanner.nextLine().trim();
        System.out.println("Please enter the password of the user:");
        String password = scanner.nextLine().trim();
        System.out.println("Please enter the phone number of the user:");
        String phoneNumber = scanner.nextLine().trim();
        System.out.println("Please enter the role(client/admin) of the user:");
        String role = scanner.nextLine().trim();

        return new User(1L, name, email, password, phoneNumber, role);
    }
}
