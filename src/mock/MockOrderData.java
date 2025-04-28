package mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Order;

public class MockOrderData {
    public static List<Order> getSampleOrders() {
        List<Order> orders = new ArrayList<>();

        orders.add(new Order(
            1,
            Arrays.asList("Burger", "Fries", "Soda"),
            LocalDateTime.of(2025, 4, 22, 14, 30),
            "Delivered"
        ));

        orders.add(new Order(
            2,
            Arrays.asList("Sushi", "Miso Soup"),
            LocalDateTime.of(2025, 4, 21, 18, 15),
            "In Progress"
        ));

        orders.add(new Order(
            3,
            Arrays.asList("Pasta", "Garlic Bread"),
            LocalDateTime.of(2025, 4, 20, 12, 45),
            "Cancelled"
        ));

        return orders;
    }
}

