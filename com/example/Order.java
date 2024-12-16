package com.example;

import java.util.*;
import java.io.*;

public class Order {
    private static int orderCounter = 0;
    private int orderID;
    private List<Cart> cartItems;
    private String orderStatus;
    private String orderDate;

    private static final String ORDER_HISTORY_FILE = "order_history.txt";

    public static List<Order> allOrders = new ArrayList<>();

    public Order(List<Cart> cartItems, String orderStatus, String orderDate) {
        this.orderID = ++orderCounter;
        this.cartItems = cartItems;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;

        allOrders.add(this);
    }

    public int getOrderID() {
        return orderID;
    }

    public List<Cart> getCartItems() {
        return cartItems;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderStatus(String newStatus) {
        this.orderStatus = newStatus;
    }

    public String orderDetails() {
        StringBuilder details = new StringBuilder("com.example.Order ID: " + orderID + "\nStatus: " + orderStatus + "\nDate: " + orderDate + "\nItems:\n");
        for (Cart item : cartItems) {
            details.append(item.getItem_name()).append(" | Quantity: ").append(item.getItem_quantity()).append(" | Cost: ").append(item.getItem_cost()).append("\n");
        }
        return details.toString();
    }

    public void saveOrderHistoryToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_HISTORY_FILE, true))) {
            writer.write(orderDetails());
            writer.write("-----\n"); // Separator for multiple orders
        } catch (IOException e) {
            System.out.println("Error saving order history: " + e.getMessage());
        }
    }

    public static void readOrderHistoryFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_HISTORY_FILE))) {
            String line;
            System.out.println("com.example.Order History:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading order history: " + e.getMessage());
        }
    }
}