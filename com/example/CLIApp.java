package com.example;

import java.util.Scanner;

public class CLIApp {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        boolean continueRunning = true;

        while (continueRunning) {
            System.out.println("Welcome to ByteMe!");
            System.out.println("1. Log in");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Log in
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    User user = User.loginUser(username, password); // Check user credentials
                    if (user != null) {
                        RoleMenu roleMenu = username.equalsIgnoreCase("admin")
                                ? new AdministratorMenu()
                                : new StudentMenu(); // Assign role based on username
                        roleMenu.displayMenu(scanner); // Show appropriate menu
                    }
                    break;

                case 2: // Register
                    System.out.print("Enter new username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    User.registerUser(newUsername, newPassword); // Save new user to file
                    break;

                case 3: // Exit
                    System.out.println("Goodbye!");
                    continueRunning = false; // End the loop
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
