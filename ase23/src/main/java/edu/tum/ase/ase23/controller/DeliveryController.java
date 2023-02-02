package edu.tum.ase.ase23.controller;

import edu.tum.ase.ase23.model.Delivery;
import edu.tum.ase.ase23.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @GetMapping("/delivery/{deliveryId}")
    public List<Delivery> getDeliveriesOfUserFromDeliveryId(@PathVariable String deliveryId) throws Exception {
        return deliveryService.getDeliveriesOfUserFromDeliveryId(deliveryId);
    }
    @GetMapping("/customer/{customerId}")
    public List<Delivery> getDeliveriesOfUserFromCustomerId(@PathVariable String customerId) throws Exception {
        return deliveryService.getDeliveriesOfUserFromCustomerId(customerId);
    }

    // Get Delivery info by ID
    @GetMapping("/id/{deliveryId}")
    public Delivery getDeliveryById(@PathVariable String deliveryId) throws Exception {
        return deliveryService.getDeliveryById(deliveryId);
    }

    // Get Delivery information by trackingID
    @GetMapping("/trackingID/{trackingID}")
    public Delivery getDeliveryByTrackingID(@PathVariable String trackingID) throws Exception {
        return deliveryService.getDeliveryByTrackingID(trackingID);
    }

    // Update Delivery
    @PostMapping("/update/{deliveryID}")
    public Delivery updateDeliveryByDeliveryID(@PathVariable String deliveryID, @RequestBody Delivery delivery) throws Exception {
        return deliveryService.updateDeliveryByDeliveryID(deliveryID, delivery);
    }
}
