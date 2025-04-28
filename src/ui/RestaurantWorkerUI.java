package ui;
import app.AuthApp;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RestaurantWorkerUI {
    public void start(Stage stage, String username) {

        // --- Header / Title ---
        Label header = new Label("Restaurant Worker Dashboard");
        header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
 
        // TODO: Create Delivery
        Label createDeliveryLabel = new Label("Create New Delivery:");
        TextField deliveryAddressField = new TextField();
        deliveryAddressField.setPromptText("Enter Delivery Address");
        Button createDeliveryBtn = new Button("Create Delivery");
 
        createDeliveryBtn.setOnAction(e -> {
            String address = deliveryAddressField.getText();
            if (!address.isEmpty()) {
                // TODO: Connect to DB to create delivery (using DAO or other logic)
                System.out.println("Creating delivery for address: " + address);
            } else {
                // Display error if address is empty
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a delivery address.");
                alert.show();
            }
        });

        VBox createDeliveryBox = new VBox(10, createDeliveryLabel, deliveryAddressField, createDeliveryBtn);
        createDeliveryBox.setAlignment(Pos.CENTER);

        // TODO: Reassign delivery
        Label reassignDeliveryLabel = new Label("Reassign Delivery:");
        TextField deliveryIdReassignField = new TextField();
        deliveryIdReassignField.setPromptText("Enter Delivery ID");
        ComboBox<String> newDriverComboBox = new ComboBox<>();
        newDriverComboBox.getItems().addAll("Driver 1", "Driver 2", "Driver 3");  // TODO: Populate from DB
        Button reassignDeliveryBtn = new Button("Reassign Delivery");

        reassignDeliveryBtn.setOnAction(e -> {
            String deliveryId = deliveryIdReassignField.getText();
            String newDriver = newDriverComboBox.getValue();
            if (!deliveryId.isEmpty() && newDriver != null) {
                // TODO: Connect to DB to reassign delivery
                System.out.println("Reassigning delivery ID " + deliveryId + " to driver: " + newDriver);
            } else {
                // Display error if any field is missing
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please provide Delivery ID and select a driver.");
                alert.show();
            }
        });

        VBox reassignDeliveryBox = new VBox(10, reassignDeliveryLabel, deliveryIdReassignField, newDriverComboBox, reassignDeliveryBtn);
        reassignDeliveryBox.setAlignment(Pos.CENTER);

        // TODO: Cancel delivery
        Label cancelDeliveryLabel = new Label("Cancel Delivery:");
        TextField cancelDeliveryIdField = new TextField();
        cancelDeliveryIdField.setPromptText("Enter Delivery ID");
        Button cancelDeliveryBtn = new Button("Cancel Delivery");

        cancelDeliveryBtn.setOnAction(e -> {
            String deliveryId = cancelDeliveryIdField.getText();
            if (!deliveryId.isEmpty()) {
                // TODO: Connect to DB to cancel delivery
                System.out.println("Canceling delivery with ID: " + deliveryId);
            } else {
                // Display error if Delivery ID is empty
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid Delivery ID.");
                alert.show();
            }
        });

        VBox cancelDeliveryBox = new VBox(10, cancelDeliveryLabel, cancelDeliveryIdField, cancelDeliveryBtn);
        cancelDeliveryBox.setAlignment(Pos.CENTER);

        // TODO: Generate reports
        Label generateReportLabel = new Label("Generate Reports:");
        Button generateReportBtn = new Button("Generate Report");

        generateReportBtn.setOnAction(e -> {
            // TODO: Connect to DB to generate reports
            System.out.println("Generating report...");
        });

        VBox generateReportBox = new VBox(10, generateReportLabel, generateReportBtn);
        generateReportBox.setAlignment(Pos.CENTER);

        // Logout Button
        Button logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> {
            stage.close();
            // Open Login UI
            Stage loginStage = new Stage();
            new AuthApp().start(loginStage); 
        });

        // Layout
        VBox layout = new VBox(20, header, createDeliveryBox, reassignDeliveryBox, cancelDeliveryBox, generateReportBox, logoutBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20;");

        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane, 400, 400);
        stage.setTitle("Restaurant Worker Dashboard");
        stage.setScene(scene);
        stage.show();
    }
    
}
