package com.example;

import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        // Launch JavaFX in the background
        new Thread(() -> Application.launch(MenuViewer.class)).start();

        // Run the CLI application
        CLIApp.run();
    }
}
