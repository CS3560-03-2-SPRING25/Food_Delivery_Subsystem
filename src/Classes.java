import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

// Abstract class representing users
public abstract class User {
    private String name;
    private String phoneNumber;
    private String email;
    
    public User(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    
    public String getName() {
        return name; 
    }

    public String getPhoneNumber() {
        return phoneNumber; 
    }

    public String getEmail() {
        return email; 
    }
}

// Customer class extending User for placing orders
public class Customer extends User {
    private int customerId;
    private String address;
    
    public Customer(int customerId, String name, String phoneNumber, String email, String address) {
        super(name, phoneNumber, email);
        this.customerId = customerId;
        this.address = address;
    }
    
    public int getCustomerId() {
        return customerId; 
    }

    public String getAddress() {
        return address; 
    }
}

// Restaurant worker class for creating orders
public class RestaurantWorker extends User {
    private int restaurantWorkerId;
    
    public RestaurantWorker(int restaurantWorkerId, String name, String phoneNumber, String email) {
        super(name, phoneNumber, email);
        this.restaurantWorkerId = restaurantWorkerId;
    }
    
    public int getRestaurantWorkerId() {
        return restaurantWorkerId; 
    }
}

// Delivery driver class responsible for deliveries
public class Driver extends User {
    private int driverId;
    private String status;
    private List<Integer> assignedOrders;
    private double rating;
    
    public Driver(int driverId, String name, String phoneNumber, String email, String status, List<Integer> assignedOrders, double rating) {
        super(name, phoneNumber, email);
        this.driverId = driverId;
        this.status = status;
        this.assignedOrders = assignedOrders;
        this.rating = rating;
    }
    
    public int getDriverId() {
        return driverId; 
    }

    public String getStatus() {
        return status;
    }

    public List<Integer> getAssignedOrders() {
        return assignedOrders;
    }
    
    public double getRating() {
        return rating;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}

// Car class for vehicle used by a delivery driver
public class Car {
    private int carId;
    private String make;
    private String model;
    private String licensePlate;
    
    public Car(int carId, String make, String model, String licensePlate) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
    }
    
    public int getCarId() {
        return carId;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getLicensePlate() { 
        return licensePlate;
    }
}

// Order class representing a food order
public class Order {
    private int orderId;
    private List<String> orderItems;
    private LocalDateTime orderTime;
    private String orderStatus;
    
    public Order(int orderId, List<String> orderItems, LocalDateTime orderTime, String orderStatus) {
        this.orderId = orderId;
        this.orderItems = orderItems;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
    }
    
    public int getOrderId() {
        return orderId;
    }

    public List<String> getOrderItems() {
        return orderItems;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

}

// Delivery class for delivery details
public class Delivery {
    private int deliveryId; 
    private Duration estimatedTime;
    private Duration deliveryTime;
    private String deliveryStatus;

    public Delivery(int deliveryId, Duration estimatedTime, Duration deliveryTime, String deliveryStatus) {
        this.deliveryId = deliveryId;
        this.estimatedTime = estimatedTime;
        this.deliveryTime = deliveryTime;
        this.deliveryStatus = deliveryStatus;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public Duration getDeliveryTime() {
        return deliveryTime;
    }

    public Duration getEstimatedTime() {
        return estimatedTime;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setEstimatedTime(Duration estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}

// Review class for storing customer reviews
public class Review {
    private int reviewId;
    private String comments;
    private int rating;
    
    public Review(int reviewId, String comments, int rating) {
        this.reviewId = reviewId;
        this.comments = comments;
        this.rating = rating;
    }
    
    public int getReviewId() {
        return reviewId;
    }

    public String getComments() {
        return comments;
    }

    public int getRating() {
        return rating;
    }
}