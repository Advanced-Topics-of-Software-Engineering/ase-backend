package edu.tum.ase.ase23.controller;

import edu.tum.ase.ase23.model.Delivery;
import edu.tum.ase.ase23.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @GetMapping("")
    public List<Delivery> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @PostMapping("")
    public Delivery createDelivery(@RequestBody Delivery Delivery) {
        return deliveryService.createDelivery(Delivery);
    }

    @GetMapping("/user/{userId}")
    public List<Delivery> getDeliveriesOfUserFromUserId(@PathVariable String userId) throws Exception {
        return deliveryService.getDeliveriesOfUserFromUserId(userId);
    }

    // Get Delivery info by ID
    @GetMapping("/{deliveryId}")
    public Delivery getDeliveryById(@PathVariable String deliveryId) throws Exception {
        return deliveryService.getDeliveryById(deliveryId);
    }

    // Get Delivery information by trackingID

    // Create Delivery

    // Update Delivery
}
