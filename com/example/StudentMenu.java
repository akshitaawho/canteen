package com.example;

import java.util.Scanner;

class StudentMenu implements RoleMenu {
    @Override
    public void displayMenu(Scanner scanner) {

        Student student = new Student();

        while (true) {
            System.out.println("THE STUDENT MENU");
            System.out.println("***MENU BROWSING***");
            System.out.println("1. View all items");
            System.out.println("2. Search for item");
            System.out.println("3. Filter by category");
            System.out.println("4. Filter by price");
            System.out.println("***CART OPERATIONS***");
            System.out.println("5. Add item");
            System.out.println("6. Modify quantity");
            System.out.println("7. Remove items");
            System.out.println("8. View total bill");
            System.out.println("9. Checkout");
            System.out.println("***ORDER TRACING***");
            System.out.println("10. View status of last order");
            System.out.println("11. Cancel last order");
            System.out.println("12. Order history");
            System.out.println("13. Log out as a student");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    student.viewAllItems();
                    break;
                case 2:
                    student.searchItem();
                    break;
                case 3:
                    student.categoryfilter();
                    break;
                case 4:
                    student.pricesort();
                    break;
                case 5:
                    student.additem();
                    break;
                case 6:
                    student.changequantity();
                    break;
                case 7:
                    student.removeitem();
                    break;
                case 8:
                    student.viewtotal();
                    break;
                case 9:
                    student.checkout();
                    break;
                case 10:
                    student.viewstatus();
                    break;
                case 11:
                    student.cancelorder();
                    break;
                case 12:
                    student.orderhistory();
                    break;
                case 13:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}