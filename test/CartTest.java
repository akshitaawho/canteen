package test;

import org.junit.Before;
import org.junit.Test;

import com.example.Cart;
import com.example.Menu;

import java.util.List;

import static org.junit.Assert.*;

public class CartTest {

    @Before
    public void setUp() {
        // Clear previous cart and menu items before each test
        Cart.cartItems.clear();
        Menu.menuItems.clear();

        // Add a sample item to the menu
        Menu.menuItems.add(new Menu("Category1", "Item1", "Available", 100));
        Menu.menuItems.add(new Menu("Category2", "Item2", "Available", 200));
    }

    @Test
    public void testAddItemToCart() {
        // Simulate adding items to the cart
        Cart.addItemToCart("Item1", 1);

        // Verify the item was added
        assertEquals(1, Cart.cartItems.size());
        assertEquals("Item1", Cart.cartItems.get(0).getItem_name());
        assertEquals(100, Cart.cartItems.get(0).getItem_cost());
        assertEquals(1, Cart.cartItems.get(0).getItem_quantity());
    }

    @Test
    public void testModifyItemQuantity() {
        // Add an item to the cart
        Cart.addItemToCart("Item1", 1);

        // Modify the quantity
        Cart.updateItemQuantity("Item1", 5);

        // Verify the quantity and total cost
        assertEquals(5, Cart.cartItems.get(0).getItem_quantity());
        assertEquals(500, Cart.cartItems.get(0).getItem_cost() * Cart.cartItems.get(0).getItem_quantity());
    }

    @Test
    public void testPreventNegativeQuantity() {
        // Add an item to the cart
        Cart.addItemToCart("Item1", 1);

        // Try to set a negative quantity
        boolean updated = Cart.updateItemQuantity("Item1", -5);

        // Verify that the quantity was not updated

    }
}