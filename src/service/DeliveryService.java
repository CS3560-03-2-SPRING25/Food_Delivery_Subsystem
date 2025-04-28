package service;

import dao.DeliveryDAO;

public class DeliveryService {
	private DeliveryDAO deliveryDAO = new DeliveryDAO();

    public boolean startDelivery(int orderId, int driverId, String address) {
        return deliveryDAO.startDelivery(orderId, driverId, address);
    }

    public boolean completeDelivery(int deliveryId) {
        return deliveryDAO.completeDelivery(deliveryId);
    }
}
