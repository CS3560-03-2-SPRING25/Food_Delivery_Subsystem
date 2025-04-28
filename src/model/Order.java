package model;

import java.time.LocalDateTime;
import java.util.List;

//Order class representing a food order
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