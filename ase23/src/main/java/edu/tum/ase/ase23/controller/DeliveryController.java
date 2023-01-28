package edu.tum.ase.ase23.controller;

import edu.tum.ase.ase23.model.Delivery;
import edu.tum.ase.ase23.service.DeliveryService;
import edu.tum.ase.ase23.service.UserService;
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

    @GetMapping("/user/{id}")
    public Delivery getDeliveriesOfUserFromUserId(@PathVariable String userId) throws Exception {
        return deliveryService.getDeliveriesOfUserFromUserId(userId);
    }
}
