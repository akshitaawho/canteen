package com.example;

import java.util.Scanner;

class AdministratorMenu implements RoleMenu {


    @Override
    public void displayMenu(Scanner scanner) {
//        System.out.print("Enter your email ID: ");
//        String email = scanner.nextLine();
//        System.out.print("Enter your password: ");
//        String password = scanner.nextLine();
//
//        // Accept any input for now
//        System.out.println("Login successful!");

        Administrator admin = new Administrator();

        while (true) {
            System.out.println("Admin com.example.Menu:");
            System.out.println("***MENU MANAGER***");
            System.out.println("1. Add items to com.example.Menu");
            System.out.println("2. Update items ");
            System.out.println("3. Remove items");
            System.out.println("***ORDER MANAGER***");
            System.out.println("4. View pending orders");
            System.out.println("5. Update order status");
            System.out.println("6. Process refunds");
            System.out.println("7. Handle special requests");
            System.out.println("**REPORT GENERATOR***");
            System.out.println("8. View Daily Sales report");
            System.out.println("9. Log out as Admin");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (option) {
                case 1:
                    admin.addmenu();
                    break;
                case 2:
                    admin.updatemenu();
                    break;
                case 3:
                    admin.removefrommenu();
                    break;
                case 4:
                    admin.viewpending();
                    break;
                case 5:
                    admin.updatestatus();
                    break;
                case 6:
                    admin.processrefunds();
                    break;
                case 7:
                    admin.handlerequests();
                    break;
                case 8:
                    admin.salesreport();
                    break;
                case 9:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}