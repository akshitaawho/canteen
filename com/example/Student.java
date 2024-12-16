package com.example;

import java.util.*;

public class Student{

    public void viewAllItems() {
        // Collect menu items
//        StringBuilder menuBuilder = new StringBuilder();
//        for (com.example.Menu item : com.example.Menu.menuItems) {
//            menuBuilder.append(item.toString()).append("\n");
//        }

        // Pass the menu content to the com.example.MenuViewer
        //com.example.MenuViewer.setMenuContent(menuBuilder.toString());

        // Show the GUI menu viewer
        MenuViewer.showMenu();

        System.out.println("GUI has been launched to view the menu.");
    }


    public void searchItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What are you looking for today?: ");
        String item_tosearch = scanner.nextLine();

        boolean itemfound = false;
        for (Menu item : Menu.menuItems) {
            if (item.getItem_name().equalsIgnoreCase(item_tosearch)) {
                System.out.println("Item found: " + item);
                itemfound = true;
                break;
            }
        }
        if (!itemfound) {
            System.out.println("Item not found in the menu.");
        }
    }

    public void categoryfilter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What category are you looking for? ");
        String item_categoryfilter = scanner.nextLine();

        boolean itemfound = false;
        for (Menu item : Menu.menuItems) {
            if (item.getItem_category().equalsIgnoreCase(item_categoryfilter)) {
                System.out.println(item);
                itemfound = true;
            }
        }
        if (!itemfound) {
            System.out.println("Item not found in the menu.");
        }
    }

    public void pricesort() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What category do you want to sort prices for? ");
        String item_pricesort = scanner.nextLine();

        boolean itemfound = false;
        List<Menu> categoryprice = new ArrayList<>();
        for (Menu item : Menu.menuItems) {
            if (item.getItem_category().equalsIgnoreCase(item_pricesort)) {
                categoryprice.add(item);
                itemfound = true;
            }
        }

        if (itemfound) {
            categoryprice.sort(Comparator.comparingInt(Menu::getItem_cost));

            for (Menu item : categoryprice) {
                System.out.println(item);
            }
        } else if (!itemfound) {
            System.out.println("Item of this category not found in the menu.");
        }
    }

    public void additem() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many items would you like to add?: ");
        int num_of_items = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < num_of_items; i++) {
            System.out.println("Entering details for item " + (i + 1) + " to add to cart:");

            System.out.println("Enter item name: ");
            String item_name = scanner.nextLine();

            boolean itemFound = false;
            for (Menu menuItem : Menu.menuItems) {
                if (menuItem.getItem_name().equalsIgnoreCase(item_name)) {
                    itemFound = true;

                    boolean itemInCart = false;
                    for (Cart cartItem : Cart.cartItems) {
                        if (cartItem.getItem_name().equalsIgnoreCase(item_name)) {
                            itemInCart = true;
                            // Update the quantity of the existing item
                            cartItem.setItem_quantity(cartItem.getItem_quantity() + 1);
                            System.out.println("Updated cart: " + cartItem.getItem_name() + ", Quantity: " + cartItem.getItem_quantity());
                            break;
                        }
                    }

                    if (!itemInCart) {
                        Cart cartItem = new Cart(
                                menuItem.getItem_category(),
                                menuItem.getItem_name(),
                                menuItem.getItem_availability(),
                                menuItem.getItem_cost(),
                                1 // Default quantity
                        );

                        Cart.cartItems.add(cartItem);
                        System.out.println("Added to cart: " + cartItem.getItem_name() + ", Quantity: " + cartItem.getItem_quantity());
                    }

                    break;
                }
            }
            if (!itemFound) {
                System.out.println("Item " + item_name + " not found in the menu.");
            }
        }
        System.out.println("\nYour com.example.Cart:");
        for (Cart cartItem : Cart.cartItems) {
            System.out.println(cartItem);
        }
    }

    public void changequantity() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which item's quantity do you want to change? ");
        for (Cart item : Cart.cartItems) {
            System.out.println(item);
        }
        String item_to_update = scanner.nextLine();

        boolean itemFound = false;
        for (Cart item : Cart.cartItems) {
            if (item.getItem_name().equalsIgnoreCase(item_to_update)) {
                itemFound = true;

                System.out.println("Enter the new quantity for " + item.getItem_name() + ": ");
                int newQuantity = scanner.nextInt();
                scanner.nextLine();

                item.setItem_quantity(newQuantity);

                System.out.println("Quantity of " + item.getItem_name() + " updated to " + newQuantity);
                break;
            }
        }
        if (!itemFound) {
            System.out.println("Item not found in the cart.");
        }
        System.out.println("\nYour com.example.Cart:");
        for (Cart cartItem : Cart.cartItems) {
            System.out.println(cartItem);
        }
    }

    public void removeitem() {
        Scanner scanner = new Scanner(System.in);

        for (Cart cartItem : Cart.cartItems) {
            System.out.println(cartItem);
        }
        System.out.println("Which item would you like to remove from the cart? ");
        String item_to_remove = scanner.nextLine();

        boolean itemFound = false;
        for (int i = 0; i < Cart.cartItems.size(); i++) {
            Cart item = Cart.cartItems.get(i);
            if (item.getItem_name().equalsIgnoreCase(item_to_remove)) {
                itemFound = true;

                Cart.cartItems.remove(i);
                System.out.println(item.getItem_name() + " has been removed from your cart.");
                break;
            }
        }
        if (!itemFound) {
            System.out.println("Item not found in the cart.");
        }
        System.out.println("\nYour com.example.Cart:");
        for (Cart cartItem : Cart.cartItems) {
            System.out.println(cartItem);
        }

    }

    public double viewtotal() {
        System.out.println("Bill details:  ");
        for (Cart cartItem : Cart.cartItems) {
            System.out.println(cartItem);
        }
        double total_bill = 0;
        for (Cart cartItem : Cart.cartItems) {
            double item_total = cartItem.getItem_quantity() * cartItem.getItem_cost();
            System.out.println(cartItem.getItem_name() + " | Quantity: " + cartItem.getItem_quantity() + " | Cost per item: " + cartItem.getItem_cost() + " | Total: " + item_total);
            total_bill += item_total;
        }

        System.out.println("Total bill: " + total_bill);
        return total_bill;
    }

    public void checkout() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to confirm your order for checkout? ");
        String orderConfirmation = scanner.nextLine();

        if (orderConfirmation.equalsIgnoreCase("yes")) {
            System.out.println("Please enter your delivery address: ");
            String deliveryAddress = scanner.nextLine();
            System.out.println("Please select mode of payment: ");
            System.out.println("1. Online payment");
            System.out.println("2. Cash on delivery");

            int paymentMode = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (paymentMode) {
                case 1:
                    System.out.println("Please enter UPI ID: ");
                    String upiId = scanner.nextLine();
                    System.out.println("Processing payment...");
                    System.out.println("Payment successful!");
                    break;
                case 2:
                    System.out.println("com.example.Order will be paid with Cash on Delivery.");
                    break;
                default:
                    System.out.println("Invalid payment method selected.");
                    return;
            }

            // Create a new order and set status to Pending
            Order newOrder = new Order(new ArrayList<>(Cart.cartItems), "Pending", java.time.LocalDate.now().toString());
            newOrder.saveOrderHistoryToFile(); // Save order to file

            System.out.println("Your order is confirmed! com.example.Order details:");
            System.out.println(newOrder.orderDetails());

            Cart.cartItems.clear(); // Clear the cart after checkout
        } else {
            System.out.println("com.example.Order not confirmed. Your cart remains unchanged.");
        }
    }



    public void viewstatus() {
        if (Order.allOrders.isEmpty()) {
            System.out.println("You have no orders placed yet.");
        }
        else {
            Order mostRecentOrder = Order.allOrders.get(Order.allOrders.size() - 1);
            System.out.println("Your most recent order details:");
            System.out.println(mostRecentOrder.orderDetails());
            System.out.println("com.example.Order Status: " + mostRecentOrder.getOrderStatus());
        }
    }

    public void cancelorder() {
        Scanner scanner = new Scanner(System.in);

        // Filter orders with status "Pending" for this student
        List<Order> pendingOrders = Order.allOrders.stream()
                .filter(order -> order.getOrderStatus().equalsIgnoreCase("Pending"))
                .toList();

        if (pendingOrders.isEmpty()) {
            System.out.println("You have no pending orders to cancel.");
            return;
        }

        System.out.println("Pending Orders:");
        for (Order order : pendingOrders) {
            System.out.println(order.orderDetails());
        }

        System.out.println("Enter the Order ID of the order you want to cancel:");
        int orderIDToCancel = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Find the order with the specified ID
        Order orderToCancel = null;
        for (Order order : pendingOrders) {
            if (order.getOrderID() == orderIDToCancel) {
                orderToCancel = order;
                break;
            }
        }

        if (orderToCancel != null) {
            // Set the order status to "Cancelled"
            orderToCancel.setOrderStatus("Cancelled");
            System.out.println("Order ID " + orderIDToCancel + " has been successfully cancelled.");
        } else {
            System.out.println("Invalid Order ID. No matching order found.");
        }
    }


    public void orderhistory() {
        Order.readOrderHistoryFromFile();
        if (Order.allOrders.isEmpty()) {
            System.out.println("You have no orders in your history.");
        }
        else {
            System.out.println("Your com.example.Order History:");

            for (Order order : Order.allOrders) {
                // Display the order details
                System.out.println(order.orderDetails());
                System.out.println("com.example.Order Status: " + order.getOrderStatus());
                System.out.println("                                         ");
            }
        }
    }

}