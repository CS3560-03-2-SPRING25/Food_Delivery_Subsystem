package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.DeliveryOrder;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Integer> {
    // Interface for database operations
    // Add custom query methods later if needed
}
