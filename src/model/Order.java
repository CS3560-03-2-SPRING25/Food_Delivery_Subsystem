package model;

import java.time.LocalDateTime;

import constants.OrderStatus;

//Order class representing a food order
public class Order {
	 private int orderId;
	 private int customerId;
	 private Integer driverId;
	 private LocalDateTime orderTime;
	 private String orderStatus;
	 
	 public Order(int orderId, int customerId, Integer driverId, LocalDateTime orderTime, String orderStatus) {
		 this.orderId = orderId;
		 this.customerId = customerId;
		 this.driverId = driverId;
		 this.orderTime = orderTime;
		 this.orderStatus = orderStatus;
	 }
	 
	 public static Order newOrderForCustomer(int customerId) {
	        return new Order(0, customerId, null, LocalDateTime.now(), OrderStatus.PENDING);
	    }
	 
	 public int getOrderId() {
	     return orderId;
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

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

}
