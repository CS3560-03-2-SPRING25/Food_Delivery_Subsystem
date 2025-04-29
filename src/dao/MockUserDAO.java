package dao;

import java.util.ArrayList;
import java.util.List;
import model.User;

public class MockUserDAO {
    private static List<User> users = new ArrayList<>();

    public MockUserDAO() {
        // Add a sample user for testing login
        users.add(User.existingUserFromDB(1, "Bob", "bob@customer.com", "1234567890", "password123", "Customer"));
        users.add(User.existingUserFromDB(2, "Bob", "bob@driver.com", "1234567890", "password123", "Driver"));
        users.add(User.existingUserFromDB(3, "Bob", "bob@worker.com", "1234567890", "password123", "RestaurantWorker"));
        System.out.print("Users created.");
    }

    // Create new user (signup)
    public int createUser(User user) {
        // Check if email already exists
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(user.getEmail())) {
                System.out.println("Email already registered.");
                int userId = 1;
                return userId;
            }
        }

        users.add(user);
        System.out.println("User created: " + user.getEmail());
        return -1;
    }

    // Login user
    public User loginUser(String email, String password) {
        System.out.println("Email entered: " + email);
        System.out.println("Password entered: " + password);

        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password)) {
                System.out.println("This is u: " + u);
                System.out.println("Login successful: " + email);
                return u;
            }
        }

        System.out.println("Invalid login for: " + email);
        return null;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        MockUserDAO.users = users;
    }
}

