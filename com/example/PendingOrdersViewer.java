package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.List;
import java.util.stream.Collectors;

public class PendingOrdersViewer extends Application {

    private static String pendingOrdersContent = "";

    public static void setPendingOrdersContent(String content) {
        pendingOrdersContent = content;
    }

    public static void showPendingOrders() {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            VBox layout = new VBox(10);
            layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

            // Create TableView for pending orders
            TableView<Order> ordersTable = new TableView<>();

            // Define columns
            TableColumn<Order, Integer> idCol = new TableColumn<>("com.example.Order ID");
            idCol.setCellValueFactory(new PropertyValueFactory<>("orderID"));

            TableColumn<Order, String> itemsCol = new TableColumn<>("Items");
            itemsCol.setCellValueFactory(order -> {
                String items = order.getValue().getCartItems().stream()
                        .map(item -> item.getItem_name() + " (x" + item.getItem_quantity() + ")")
                        .collect(Collectors.joining(", "));
                return new javafx.beans.property.SimpleStringProperty(items);
            });

            TableColumn<Order, String> statusCol = new TableColumn<>("Status");
            statusCol.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

            TableColumn<Order, String> dateCol = new TableColumn<>("Date");
            dateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

            // Add columns to table
            ordersTable.getColumns().addAll(idCol, itemsCol, statusCol, dateCol);

            // Fetch pending orders
            List<Order> pendingOrders = Order.allOrders.stream()
                    .filter(order -> order.getOrderStatus().equalsIgnoreCase("Pending"))
                    .collect(Collectors.toList());
            ObservableList<Order> pendingOrdersList = FXCollections.observableArrayList(pendingOrders);
            ordersTable.setItems(pendingOrdersList);

            layout.getChildren().add(ordersTable);

            Scene scene = new Scene(layout, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Pending Orders Viewer");
            stage.show();
        });
    }



    @Override
    public void start(Stage primaryStage) {
        // Required for JavaFX initialization but unused here.
    }

    // Helper method to filter pending orders
    public static void generatePendingOrdersContent() {
        // Filter orders with "Pending" status
        List<Order> pendingOrders = Order.allOrders.stream()
                .filter(order -> order.getOrderStatus().equalsIgnoreCase("Pending"))
                .collect(Collectors.toList());

        if (pendingOrders.isEmpty()) {
            setPendingOrdersContent("No pending orders.");
        } else {
            StringBuilder builder = new StringBuilder();
            for (Order order : pendingOrders) {
                builder.append("com.example.Order ID: ").append(order.getOrderID()).append("\n");
                builder.append(order.orderDetails()).append("\n");
                builder.append("-----\n");
            }
            setPendingOrdersContent(builder.toString());
        }
    }


}
