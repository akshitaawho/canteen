package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MenuViewer extends Application {

//    private static String menuContent = "";
//
//    public static void setMenuContent(String content) {
//        menuContent = content;
//    }

    public static void showMenu() {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            VBox layout = new VBox(10);
            layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

            // Create TableView for menu items
            TableView<Menu> menuTable = new TableView<>();

            // Define columns
            TableColumn<Menu, String> nameCol = new TableColumn<>("Name");
            nameCol.setCellValueFactory(new PropertyValueFactory<>("item_name"));

            TableColumn<Menu, String> categoryCol = new TableColumn<>("Category");
            categoryCol.setCellValueFactory(new PropertyValueFactory<>("item_category"));

            TableColumn<Menu, Integer> costCol = new TableColumn<>("Cost");
            costCol.setCellValueFactory(new PropertyValueFactory<>("item_cost"));

            TableColumn<Menu, String> availabilityCol = new TableColumn<>("Availability");
            availabilityCol.setCellValueFactory(new PropertyValueFactory<>("item_availability"));

            // Add columns to table
            menuTable.getColumns().addAll(nameCol, categoryCol, costCol, availabilityCol);

            // Add data to table
            ObservableList<Menu> menuItems = FXCollections.observableArrayList(Menu.menuItems);
            menuTable.setItems(menuItems);

            // Add a button to navigate to Pending Orders
            Button viewPendingOrdersButton = new Button("View Pending Orders");
            viewPendingOrdersButton.setOnAction(e -> PendingOrdersViewer.showPendingOrders()); // Show Pending Orders GUI

            layout.getChildren().addAll(menuTable, viewPendingOrdersButton); // Add both the table and button to the layout

            Scene scene = new Scene(layout, 600, 400);
            stage.setScene(scene);
            stage.setTitle("com.example.Menu Viewer");
            stage.show();
        });
    }

    @Override
    public void start(Stage primaryStage) {
        // This method is required for JavaFX initialization but remains unused.
    }
}
