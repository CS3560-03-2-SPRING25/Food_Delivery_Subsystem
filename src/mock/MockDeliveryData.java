// data/MockDeliveryData.java
package mock;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import model.Delivery;

public class MockDeliveryData {
    public static List<Delivery> getSampleDeliveries() {
        List<Delivery> deliveries = new ArrayList<>();

        deliveries.add(new Delivery(100, Duration.ofMinutes(30), Duration.ofMinutes(25), "Delivered", "123 Main St"));
        deliveries.add(new Delivery(101, Duration.ofMinutes(45), Duration.ofMinutes(50), "In Transit", "456 Oak Ave"));
        deliveries.add(new Delivery(102, Duration.ofMinutes(20), Duration.ZERO, "Preparing", "789 Pine Rd"));

        return deliveries;
    }
}
