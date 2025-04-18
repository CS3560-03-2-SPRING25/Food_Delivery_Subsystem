package model;

import java.time.Duration;

//Delivery class for delivery details
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
