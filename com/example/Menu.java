package com.example;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    public static List<Menu> menuItems = new ArrayList<>();

    private String item_category;
    private String item_name;
    private int item_cost;
    private String item_availability;


    public Menu(String item_category, String item_name, String item_availability, int item_cost) {
        this.item_category = item_category;
        this.item_name = item_name;
        this.item_availability = item_availability; // Correctly passed as a String
        this.item_cost = item_cost; // Correctly passed as an int
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

    @Override
    public String toString() {
        return "Name: " + item_name +
                ", Category: " + item_category +
                ", Cost: " + item_cost +
                ", Availability: " + item_availability;
    }
}