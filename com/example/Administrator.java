package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.*;

public class Administrator {

    public void addmenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many items would you like to add?: ");
        int num_of_items = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < num_of_items; i++) {
            System.out.println("Entering details for item " + (i + 1) + ":");

            System.out.println("Enter item name: ");
            String item_name = scanner.nextLine();
            System.out.println("Enter item category: ");
            String item_category = scanner.nextLine();
            System.out.println("Enter item cost: ");
            int item_cost = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter item availabilty: ");
            String item_availability = scanner.nextLine();

            Menu newItem = new Menu(item_category, item_name, item_availability, item_cost);
            Menu.menuItems.add(newItem);
            System.out.println("The following item has successfully been added! " + newItem);

        }
    }

    public void updatemenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which Item would you like to update?");
        for (Menu item : Menu.menuItems) {
            System.out.println(item);
        }
        String item_to_update = scanner.nextLine();

        boolean itemfound = false;
        for (Menu item : Menu.menuItems) {
            if (item.getItem_name().equalsIgnoreCase(item_to_update)) {
                System.out.println("Item found: " + item);
                itemfound = true;

                System.out.println("Enter item name: ");
                String new_name = scanner.nextLine();
                System.out.println("Enter item category: ");
                String new_category = scanner.nextLine();
                System.out.println("Enter item cost: ");
                int new_cost = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter item availabilty: ");
                String new_availability = scanner.nextLine();

                item.setItem_name(new_name);
                item.setItem_category(new_category);
                item.setItem_cost(new_cost);
                item.setItem_availability(new_availability);

                System.out.println("The following item was updated successfully: " + item);
                break;
            }
        }
        if (!itemfound) {
            System.out.println("Item not found in the menu.");
        }
    }

    public void removefrommenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which Item would you like to remove from the menu?");
        for (Menu item : Menu.menuItems) {
            System.out.println(item);
        }
        String item_to_remove = scanner.nextLine();

        boolean itemfound = false;
        Iterator<Menu> iterator = Menu.menuItems.iterator();
        while (iterator.hasNext()) {
            Menu item = iterator.next();
            if (item.getItem_name().equalsIgnoreCase(item_to_remove)) {
                System.out.println("Item found and removed: " + item);
                iterator.remove();
                itemfound = true;
                break;
            }
        }

        if (!itemfound) {
            System.out.println("Item not found in the menu.");
        }
    }

    public void viewpending() {
        List<Order> pendingOrders = new ArrayList<>();
        for (Order order : Order.allOrders) {
            if (order.getOrderStatus().equalsIgnoreCase("Pending")) {
                pendingOrders.add(order);
            }
        }

        if (pendingOrders.isEmpty()) {
            System.out.println("There are no pending orders at the moment.");
        }
        else {
            System.out.println("Pending Orders:");
            for (Order order : pendingOrders) {
                System.out.println(order.orderDetails());
            }
        }
    }

    public void updatestatus() {
        viewpending();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the OrderID of the order you want to update: ");
        int orderID = scanner.nextInt();
        scanner.nextLine();

        Order orderToUpdate = null;
        for (Order order : Order.allOrders) {
            if (order.getOrderID() == orderID) {
                orderToUpdate = order;
                break;
            }
        }

        if (orderToUpdate.getOrderStatus().equalsIgnoreCase("Pending")) {
            System.out.println("com.example.Order found: " + orderToUpdate.orderDetails());
            System.out.println("Select new status for this order: ");
            System.out.println("1. Completed");
            System.out.println("2. Canceled");

            int statusChoice = scanner.nextInt();
            scanner.nextLine();

            String newStatus = "";
            switch (statusChoice) {
                case 1:
                    newStatus = "Completed";
                    break;
                case 2:
                    newStatus = "Canceled";
                    break;
                default:
                    System.out.println("Invalid status selected.");
                    return;
            }

            // Update the order's status
            orderToUpdate.setOrderStatus(newStatus);
            System.out.println("com.example.Order status updated to: " + newStatus);
        }
        else {
            System.out.println("That is not a Pending order.");
        }
    }

    public void processrefunds() {
        Scanner scanner = new Scanner(System.in);

        // Filter orders with status "Cancelled"
        List<Order> cancelledOrders = Order.allOrders.stream()
                .filter(order -> order.getOrderStatus().equalsIgnoreCase("Cancelled"))
                .toList();

        if (cancelledOrders.isEmpty()) {
            System.out.println("There are no orders to process refunds for.");
            return;
        }

        System.out.println("Cancelled Orders:");
        for (Order order : cancelledOrders) {
            System.out.println(order.orderDetails());
        }

        System.out.println("Enter the Order ID of the order you want to process a refund for:");
        int orderIDToRefund = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Find the order with the specified ID
        Order orderToRefund = null;
        for (Order order : cancelledOrders) {
            if (order.getOrderID() == orderIDToRefund) {
                orderToRefund = order;
                break;
            }
        }

        if (orderToRefund != null) {
            // Calculate the total bill for the order
            double totalRefundAmount = orderToRefund.getCartItems().stream()
                    .mapToDouble(item -> item.getItem_cost() * item.getItem_quantity())
                    .sum();

            System.out.println("Refund processed for Order ID " + orderIDToRefund + ".");
            System.out.println("Refund Amount: $" + totalRefundAmount);
        } else {
            System.out.println("Invalid Order ID. No matching order found.");
        }
    }


    public void handlerequests() {

    }

    public void salesreport() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the date for which you want the sales report (yyyy-mm-dd): ");
        String reportDate = scanner.nextLine();

        // Filter completed orders for the given date
        List<Order> completedOrders = Order.allOrders.stream()
                .filter(order -> order.getOrderStatus().equalsIgnoreCase("Completed") &&
                        order.getOrderDate().equals(reportDate))
                .toList();

        if (completedOrders.isEmpty()) {
            System.out.println("No completed orders found for the given date: " + reportDate);
            return;
        }

        // Calculate total sales and count total orders
        double totalSales = 0;
        int totalOrders = completedOrders.size();
        Map<String, Integer> itemPopularity = new HashMap<>();

        for (Order order : completedOrders) {
            for (Cart item : order.getCartItems()) {
                totalSales += item.getItem_cost() * item.getItem_quantity();

                // Track item popularity
                itemPopularity.put(
                        item.getItem_name(),
                        itemPopularity.getOrDefault(item.getItem_name(), 0) + item.getItem_quantity()
                );
            }
        }

        // Find the most popular item(s)
        int maxQuantity = itemPopularity.values().stream().max(Integer::compare).orElse(0);
        List<String> mostPopularItems = itemPopularity.entrySet().stream()
                .filter(entry -> entry.getValue() == maxQuantity)
                .map(Map.Entry::getKey)
                .toList();

        // Display the report
        System.out.println("\nSales Report for " + reportDate + ":");
        System.out.println("Total Orders Processed: " + totalOrders);
        System.out.println("Total Sales Amount: $" + totalSales);
        System.out.println("Most Popular Item(s): " + String.join(", ", mostPopularItems) +
                " (Sold " + maxQuantity + " units)");
    }

}