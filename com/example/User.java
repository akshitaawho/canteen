package com.example;

import java.io.*;
import java.util.*;

public class User {
    private String username;
    private String password;

    private static String USER_DATA_FILE = "user_data.txt";

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public static void setUserDataFile(String filePath) {
        USER_DATA_FILE = filePath;
    }

    public static void registerUser(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE, true))) {
            writer.write(username + "," + password);
            writer.newLine();
            System.out.println("com.example.User registered successfully!");
        } catch (IOException e) {
            System.out.println("Error registering user: " + e.getMessage());
        }
    }

    public static User loginUser(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length ==2 && parts[0].equals(username) && parts[1].equals(password)) {
                    System.out.println("Login successful!");
                    return new User(username, password);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading user data: " + e.getMessage());
        }
        System.out.println("Invalid username or password.");
        return null;
    }

    @Override
    public String toString() {
        return "Username: " + username;
    }
}
