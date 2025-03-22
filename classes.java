// In your choice of programming language, develop your system classes' skeleton. Make sure to include attributes and their types. (10 Pts.)
// Think about use cases and develop your methods skeleton, think carefully about each method's input parameters and returned output (if any). (10 Pts.)
// Comment all your codes meaningfully.


public abstract class User {
  String name;
  String phoneNumber;
  String email;

  public User(String name, String email) {
      // ...
  }

  public String getName() {
      // ...
  }

  public String getPhoneNumber() {
       // ...
  }

  public String getEmail() {
      // ...
  }
  
}


public class Customer extends User {
  int customerID; // Can be UUID
  String address;
  
  public Customer(String name, String email, int customerID, String address) {
      super(name, email);
      // ...
  }

  public String getAddress() {
      // ...
  }
}

public class RestaurantWorker extends User {
  int restaurantWorkerID; // Can be UUID
  
  public RestaurantWorker(String name, String email, int restaurantWorkerID) {
      super(name, email);
      // ...
  }
}

public class Driver extends User {
  int driverID; // Can be UUID
  String status;
  List<Order> assignedOrders;
  List<Review> reviews;
  
  public Driver(String name, String email, int driverID, String status, List<Order> assignedOrders, List<Review> reviews) {
      super(name, email);
      // ...
  }

  public String getStatus() {
      // ...
  }

  public void setStatus(String status) {
      // ...
  }
}

public class Car {
  int carID; // Can be UUID
  String make;
  String model;
  String licensePlate;

  public Car(int carID, String make, String model, String licensePlate) {
      // ...
  }

  public String getMake() {
      // ...
  }

  public String getModel() {
       // ...
  } 
}

public class Delivery {
  int deliveryID; // Can be UUID
  Duration deliveryTime; // Can be LocalDateTime 
  String deliveryStatus; 
  
  public Delivery(int deliveryID, Duration deliveryTime, String deliveryStatus) {
      // ...
  }

  // We can also calculate this based on the time the order was completed... 
  public Duration getDeliveryTime() {
      // ...
  }

  // Can be calculated based on the time the order was placed
  public Duration getEstimatedTime() {
      // ...
  }

  public void setEstimatedTime(Duration estimatedTime) {
    // ...
  }
}

public class Order {
  int orderID; // Can be UUID
  List<String> orderItems; 
  LocalDateTime orderTime;
  String orderStatus;
  
  public Order(int orderID, List<String> orderItems, LocalDateTime orderTime, String orderStatus) {
      // ...
  }

}




