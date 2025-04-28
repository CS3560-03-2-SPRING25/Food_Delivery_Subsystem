package ui;
import app.AuthApp;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import mock.MockDeliveryData;
import model.Delivery;

public class CustomerUI {

    public void start(Stage stage, String username) {
        // Layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        // Top - Header
        HBox header = new HBox(10);
        Label welcomeLabel = new Label("Welcome, " + username + "!");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Button logoutBtn = new Button("Logout");
        header.getChildren().addAll(welcomeLabel, spacer, logoutBtn);
        root.setTop(header);

        // Logout button
        logoutBtn.setOnAction(e -> {
            stage.close();
            // Open login UI
            Stage loginStage = new Stage();
            new AuthApp().start(loginStage);
        });

        // Center: track delivery and rate
        VBox centerBox = new VBox(20);

        // Place Order
        Label placeOrderLabel = new Label("Place a New Order:");
        TextField foodItemsField = new TextField();
        foodItemsField.setPromptText("Enter food items (comma separated)");
        TextField deliveryAddressField = new TextField();
        deliveryAddressField.setPromptText("Enter Delivery Address");

        Button placeOrderBtn = new Button("Place Order");
        Label orderResultLabel = new Label();

        placeOrderBtn.setOnAction(e -> {
            String foodItems = foodItemsField.getText();
            String deliveryAddress = deliveryAddressField.getText();
            
            if (foodItems.isEmpty() || deliveryAddress.isEmpty()) {
                orderResultLabel.setText("Please fill in all fields.");
            } else {
                // TODO: Connect to OrderDAO to actually place the order in DB

                System.out.println("Placing Order: " + foodItems + " to " + deliveryAddress);

                // Mock behavior
                orderResultLabel.setText("Order placed successfully!");

                // Clear the fields after placing order
                foodItemsField.clear();
                deliveryAddressField.clear();
            }
        });

        // VBox to group Place Order section
        VBox placeOrderSection = new VBox(5, placeOrderLabel, foodItemsField, deliveryAddressField, placeOrderBtn, orderResultLabel);
        // placeOrderSection.setPadding(new Insets(10));

        // Track Delivery
        Label trackLabel = new Label("Track Delivery:");
        TextField deliveryIdField = new TextField();
        deliveryIdField.setPromptText("Enter Delivery ID");
        Button trackBtn = new Button("Track");
        Label trackingResult = new Label();

        VBox trackingSection = new VBox(5, trackLabel, deliveryIdField, trackBtn, trackingResult);

        // Rate 
        Label rateLabel = new Label("Rate Our Service:");

        Slider ratingSlider = new Slider(1, 5, 3); // min=1, max=5, default=3
        ratingSlider.setMajorTickUnit(1);
        ratingSlider.setMinorTickCount(0);
        ratingSlider.setBlockIncrement(1);
        ratingSlider.setSnapToTicks(true);
        ratingSlider.setShowTickLabels(true);
        ratingSlider.setShowTickMarks(true);
        Button submitRating = new Button("Submit Rating");
        Label ratingMsg = new Label();

        submitRating.setOnAction(e -> {
            int rating = (int) ratingSlider.getValue();
            ratingMsg.setText("Thanks for rating us " + rating + " stars!");
            // TODO: Save rating to DB
        });

        VBox ratingSection = new VBox(5, rateLabel, ratingSlider, submitRating, ratingMsg);
        ratingSection.setVisible(false); // Hide rating system until a valid delivery is found

        // Track button
        trackBtn.setOnAction(e -> {
            String deliveryId = deliveryIdField.getText();
            boolean found = false;

            // TODO: Get tracking data and display
        
            for (Delivery d : MockDeliveryData.getSampleDeliveries()) {
                if (String.valueOf(d.getDeliveryId()).equals(deliveryId)) {
                    trackingResult.setText(
                        String.format("Status: %s, ETA: %d mins", 
                            d.getDeliveryStatus(), 
                            d.getEstimatedTime().toMinutes()
                        )
                    );
                    ratingSection.setVisible(true); // Make rating system visible if tracking data exists
                    found = true;
                    break;
                }
            }
        
            if (!found) {
                trackingResult.setText("Delivery not found.");
                ratingSection.setVisible(false); // Hide rating system if tracking data does not exist
            }
        });

        centerBox.getChildren().addAll(placeOrderSection, trackingSection, new Separator(), ratingSection);

        ScrollPane scrollPane = new ScrollPane(centerBox);
        scrollPane.setFitToWidth(true);
        root.setCenter(scrollPane);

        // Scene and Stage
        Scene scene = new Scene(root, 400, 350);
        stage.setTitle("Customer Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}
