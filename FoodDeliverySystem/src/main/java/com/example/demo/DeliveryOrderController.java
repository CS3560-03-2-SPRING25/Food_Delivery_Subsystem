package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.DeliveryOrder;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*") // Allows frontend to connect
public class DeliveryOrderController {
    @Autowired
    private DeliveryOrderRepository orderRepository;

    // GET all orders
    @GetMapping
    public List<DeliveryOrder> getOrderItems() {
        return orderRepository.findAll(); // pulls data from mySQL
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        DeliveryOrder order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setOrderStatus(body.get("status"));
            orderRepository.save(order);
            return ResponseEntity.ok("Status updated");
        }
        return ResponseEntity.notFound().build();
    }
}
