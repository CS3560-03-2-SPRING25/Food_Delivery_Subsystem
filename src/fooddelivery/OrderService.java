package fooddelivery;

import java.util.List;

import constants.DriverStatus;
import dao.DriverDAO;
import dao.OrderDAO;
import model.Driver;
import model.Order;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();

    public boolean placeOrder(int customerId) {
        Order order = Order.newOrderForCustomer(customerId);
        int orderId = orderDAO.createOrder(order);
        if (orderId > 0) {
            System.out.println("Order placed successfully! Order ID: " + orderId);
            return true;
        } else {
            System.out.println("Order placement failed.");
            return false;
        }
    }
    
    public boolean acceptOrder(int orderId) {
        boolean success = orderDAO.acceptOrder(orderId);
        if (success) {
            System.out.println("Order accepted successfully!");
        } else {
            System.out.println("Failed to accept order. Maybe already accepted or does not exist.");
        }
        return success;
    }
    
    public List<Order> viewAllOrders() {
        return orderDAO.getAllOrders();
    }

    public boolean cancelOrder(int orderId) {
        boolean success = orderDAO.cancelOrder(orderId);
        if (success) {
            System.out.println("Order cancelled successfully!");
        } else {
            System.out.println("Failed to cancel order.");
        }
        return success;
    }

    public boolean markOrderReady(int orderId) {
        boolean success = orderDAO.markOrderReady(orderId);
        if (success) {
            System.out.println("Order is now ready for delivery!");
        } else {
            System.out.println("Failed to mark order as ready.");
        }
        return success;
    }

    public boolean autoAssignDriver(int orderId) {
    	DriverDAO driverDAO = new DriverDAO();
        Driver availableDriver = driverDAO.findAvailableDriver();
        if (availableDriver == null) {
            System.out.println("No available drivers at the moment.");
            return false;
        }

        boolean assigned = orderDAO.assignDriverToOrder(orderId, availableDriver.getUserId());
        if (assigned) {
            driverDAO.updateDriverStatus(availableDriver.getUserId(), DriverStatus.BUSY);
            System.out.println("Driver " + availableDriver.getUserId() + " assigned to order " + orderId);
            return true;
        } else {
            System.out.println("Failed to assign driver.");
            return false;
        }
    }


}

