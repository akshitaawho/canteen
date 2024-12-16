package com.example;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    public static List<Cart> cartItems = new ArrayList<>();

    private String item_category;
    private String item_name;
    private int item_cost;
    private String item_availability;
    private int item_quantity;

    public Cart(String item_category, String item_name, String item_availability, int item_cost, int item_quantity) {
        this.item_category = item_category;
        this.item_name = item_name;
        this.item_availability = item_availability; // Correctly passed as a String
        this.item_cost = item_cost; // Correctly passed as an int
        this.item_quantity = item_quantity;
    }

    public String getItem_category() {
        return item_category;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_availability() {
        return item_availability;
    }

    public int getItem_cost() {
        return item_cost;
    }

    public int getItem_quantity() {
        return item_quantity;
    }

    public void setItem_category(String item_category){
        this.item_category = item_category;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setItem_cost(int item_cost){
        this.item_cost = item_cost;
    }

    public void setItem_availability(String item_availability) {
        this.item_availability = item_availability;
    }

    public void setItem_quantity(int item_quantity) {
        this.item_quantity = item_quantity;
    }

    @Override
    public String toString() {
        return "Name: " + item_name + ", Category: " + item_category + ", Cost: " + item_cost +
                ", Availability: " + item_availability + ", Quantity: " + item_quantity;
    }

    public static void addItemToCart(String itemName, int quantity) {
        for (Menu menuItem : Menu.menuItems) {
            if (menuItem.getItem_name().equalsIgnoreCase(itemName)) {
                // Check if the item is already in the cart
                for (Cart cartItem : cartItems) {
                    if (cartItem.getItem_name().equalsIgnoreCase(itemName)) {
                        cartItem.setItem_quantity(cartItem.getItem_quantity() + quantity);
                        return;
                    }
                }
                // Add a new item to the cart
                cartItems.add(new Cart(
                        menuItem.getItem_category(),
                        menuItem.getItem_name(),
                        menuItem.getItem_availability(),
                        menuItem.getItem_cost(),
                        quantity
                ));
                return;
            }
        }
        throw new IllegalArgumentException("Item not found in menu: " + itemName);
    }

    // Update quantity for an existing item
    public static boolean updateItemQuantity(String itemName, int quantity) {
        if (quantity < 0) {
            return false;
        }
        for (Cart cartItem : cartItems) {
            if (cartItem.getItem_name().equalsIgnoreCase(itemName)) {
                cartItem.setItem_quantity(quantity);
                return true;
            }
        }
        throw new IllegalArgumentException("Item not found in cart: " + itemName);
    }

    // Calculate the total cost of the cart
    public static double calculateTotal() {
        double total = 0;
        for (Cart cartItem : cartItems) {
            total += cartItem.getItem_cost() * cartItem.getItem_quantity();
        }
        return total;
    }
}