package ui;
import app.AuthApp;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import mock.MockOrderData; //used mock data
import model.Order;
//import dao.DeliveryDAO;

public class DriverUI {
    public void start(Stage stage, String username) {

        // Buttons for navigation
        Button homeBtn = new Button("Home");
        Button ordersBtn = new Button("Orders");

        // Navigation bar
        HBox navBtns = new HBox(10, homeBtn, ordersBtn);
        navBtns.setAlignment(Pos.CENTER_LEFT);

        // Username
        Label usernameLabel = new Label(username);
        HBox usernameBox = new HBox(usernameLabel);
        usernameBox.setAlignment(Pos.CENTER_RIGHT);

        // Combine nav and username in a BorderPane
        BorderPane topBar = new BorderPane();
        topBar.setLeft(navBtns);
        topBar.setRight(usernameBox);
        topBar.setPadding(new Insets(10));
        topBar.setStyle("-fx-background-color: #EEEEEE;");

        // Placeholder for center content
        VBox centerArea = new VBox(10);
        centerArea.setPadding(new Insets(20));

        // Main layout
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(centerArea);

        // Home button
        homeBtn.setOnAction(e -> {
            centerArea.getChildren().clear();
            Button logoutBtn = new Button("Logout");
            centerArea.getChildren().addAll(new Label("This is the home page."), logoutBtn);

            // Logout button
            logoutBtn.setOnAction(event -> {
            stage.close();
            // Open Login UI
            Stage loginStage = new Stage();
            new AuthApp().start(loginStage); 
            });
        });

        

        // Orders button
        ordersBtn.setOnAction(e -> {
            centerArea.getChildren().clear();

            // Header
            Label header = new Label("All Orders");
            header.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

            // Divider
            Line divider = new Line(0, 0, 300, 0);
            
            // Orders
            VBox orderBox = new VBox(10);
            orderBox.setPadding(new Insets(15));

            // TODO: Get delivery data from DB
            List<Order> mockOrders = MockOrderData.getSampleOrders();

            for (Order order : mockOrders) {
                String items = String.join(", ", order.getOrderItems());
                String orderInfo = String.format(
                    "Order #%d\nItems: %s\nTime: %s\nStatus: %s",
                    order.getOrderId(),
                    items,
                    order.getOrderTime().toString(),
                    order.getOrderStatus()
                );

                Label orderLabel = new Label(orderInfo);
            
                VBox singleOrder = new VBox(5); // new VBox for each order
                singleOrder.setPadding(new Insets(10));
                singleOrder.setStyle("-fx-border-color: gray; -fx-background-color: #f2f2f2;");
                orderLabel.setWrapText(true); // allows multiline
                orderLabel.setMaxWidth(Double.MAX_VALUE); // allow it to fill VBox width
                singleOrder.setMaxWidth(Double.MAX_VALUE);
                VBox.setVgrow(singleOrder, Priority.ALWAYS);

                // Mark as delivered button
                Button markDeliveredBtn = new Button("Mark as Delivered");
                markDeliveredBtn.setOnAction(event-> {
                    // TODO: Update database and mark order as delivered 
                    orderBox.getChildren().remove(singleOrder); // Removes order from UI
                    System.out.println("Order marked as delivered.");
                });

                singleOrder.getChildren().addAll(orderLabel, markDeliveredBtn);
                orderBox.getChildren().add(singleOrder);
            }
            ScrollPane scrollPane = new ScrollPane(orderBox);
            scrollPane.setFitToWidth(true);
            centerArea.getChildren().addAll(header, divider, scrollPane);
        });

        // Default view
        homeBtn.fire();

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Food Delivery App");
        stage.show();
    }

}
