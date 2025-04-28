package app;
import dao.MockUserDAO;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.User;
import ui.CustomerUI;
import ui.DriverUI;
import ui.RestaurantWorkerUI;


public class AuthApp extends Application {

    MockUserDAO userDAO = new MockUserDAO();

    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        stage.setTitle("Food Delivery Login");

        // Start with login
        stage.setScene(createLoginScene());
        stage.show();
    }
    // Login 
    private Scene createLoginScene() {
        VBox loginBox = new VBox(10);
        loginBox.setPadding(new Insets(20));

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label errorLabel = new Label();


        Button loginBtn = new Button("Login");
        loginBtn.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            // TODO: Login
            User loggedInUser = userDAO.loginUser(email, password);  // Call the DAO here
            System.out.println("Logged in user from AuthApp: " + loggedInUser);

            if (loggedInUser != null) {
                String role = loggedInUser.getRole();
                // Opens the appropriate UI
                // TODO: get username
                String username = "Mandy"; // example
                openRoleWindow(role, username);
            } else {
                errorLabel.setText("Invalid email or password.");
            }
        });
                

        Label switchToSignup = new Label("No account? Sign up here.");
        switchToSignup.setStyle("-fx-text-fill: blue; -fx-underline: true;");
        switchToSignup.setOnMouseClicked(e -> primaryStage.setScene(createSignupScene()));

        loginBox.getChildren().addAll(
            new Label("Login"), emailLabel, emailField,
            passwordLabel, passwordField,
            loginBtn, switchToSignup
        );

        return new Scene(loginBox, 300, 300);
    }

    // Sign up
    private Scene createSignupScene() {
        VBox signupBox = new VBox(10);
        signupBox.setPadding(new Insets(20));

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label roleLabel = new Label("Account Type:");
        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Customer", "Driver", "Restaurant Worker");

        Button signupBtn = new Button("Sign Up");
        Label signUpMessage = new Label();

        signupBtn.setOnAction(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String password = passwordField.getText();
            String selectedRole = roleComboBox.getValue();
            // TODO: Create user
            if (validateSignUp(name, email, password, phone)) {
                // Create a User object using the factory method
                User newUser = User.newUserForSignup(name, email, phone, password, selectedRole);
                // Insert the new user into the database using DAO
                int user_id = userDAO.createUser(newUser);
                
                // Show success message or navigate
                if (user_id > -1) {
                    System.out.println("User successfully created!");
                    signUpMessage.setText("User created successfully!");
                } else {
                    System.out.println("Error creating user.");
                }
            } else {
                signUpMessage.setText("Please fill out all fields.");
            }
        });

        Label switchToLogin = new Label("Already have an account? Login here.");
        switchToLogin.setStyle("-fx-text-fill: blue; -fx-underline: true;");
        switchToLogin.setOnMouseClicked(e -> primaryStage.setScene(createLoginScene()));

        ScrollPane scrollPane = new ScrollPane(signupBox);
        scrollPane.setFitToWidth(true);

        signupBox.getChildren().addAll(
            new Label("Sign Up"),
            nameLabel, nameField,
            emailLabel, emailField,
            phoneLabel, phoneField,
            passwordLabel, passwordField,
            roleLabel, roleComboBox,
            signupBtn,
            switchToLogin
        );

        return new Scene(scrollPane, 300, 400);
    }

    // Open window based on role
    private void openRoleWindow(String role, String username) {
        Stage stage = new Stage();
    
        switch (role) {
            case "Customer":
                new CustomerUI().start(stage, username);
                break;
            case "Driver":
                new DriverUI().start(stage, username);
                break;
            case "RestaurantWorker":
                new RestaurantWorkerUI().start(stage, username);
                break;
            default:
                showAlert("Unknown role: " + role);
        }
    }
    

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean validateSignUp(String name, String email, String password, String phone) {
        return !(name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty());
    }
    

    public static void main(String[] args) {
        launch();
    }
}
