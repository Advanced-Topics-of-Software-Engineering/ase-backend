package edu.tum.ase.ase23.controller;

import edu.tum.ase.ase23.model.Delivery;
import edu.tum.ase.ase23.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @GetMapping("")
    public ResponseEntity<?> getAllDeliveries() {
        return ResponseEntity.ok(deliveryService.getAllDeliveries());
    }

    @PostMapping("")
    public ResponseEntity<?> createDelivery(@RequestBody Delivery Delivery) {
        return ResponseEntity.ok(deliveryService.createDelivery(Delivery));
        // new Delivery(dto.get(userId), .. , )
    }

    @GetMapping("/deliverer/{delivererId}")
    public ResponseEntity<?> getDeliveriesOfUserFromDelivererId(@PathVariable String delivererId) throws Exception {
        return ResponseEntity.ok(deliveryService.getDeliveriesOfUserFromDelivererId(delivererId));
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getDeliveriesOfUserFromCustomerId(@PathVariable String customerId) throws Exception {
        return ResponseEntity.ok(deliveryService.getDeliveriesOfUserFromCustomerId(customerId));
    }

    // Get Delivery info by ID
    @GetMapping("/id/{deliveryId}")
    public ResponseEntity<?> getDeliveryById(@PathVariable String deliveryId) throws Exception {
        return ResponseEntity.ok(deliveryService.getDeliveryById(deliveryId));
    }

    // Get Delivery information by trackingID
    @GetMapping("/trackingID/{trackingID}")
    public ResponseEntity<?> getDeliveryByTrackingID(@PathVariable String trackingID) throws Exception {
        return ResponseEntity.ok(deliveryService.getDeliveryByTrackingID(trackingID));
    }

    // Get deliveries of customer by box id
    @GetMapping("/customer/{customerID}/box/{boxID}")
    public ResponseEntity<?> getDeliveriesOfCustomerByBoxID(@PathVariable String customerID, @PathVariable String boxID) throws Exception {
        return ResponseEntity.ok(deliveryService.getDeliveriesOfCustomerByBoxID(customerID, boxID));
    }

    // Get deliveries of deliverer by box id
    @GetMapping("/deliverer/{delivererID}/box/{boxID}")
    public ResponseEntity<?> getDeliveriesOfDelivererByBoxID(@PathVariable String delivererID, @PathVariable String boxID) throws Exception {
        return ResponseEntity.ok(deliveryService.getDeliveriesOfDelivererByBoxID(delivererID, boxID));
    }

    // Update Delivery
    @PostMapping("/update/{deliveryID}")
    public ResponseEntity<?> updateDeliveryByDeliveryID(@PathVariable String deliveryID, @RequestBody Delivery delivery) throws Exception {
        return ResponseEntity.ok(deliveryService.updateDeliveryByDeliveryID(deliveryID, delivery));
    }

    @GetMapping("/customer/{customerID}/box/{boxID}/status/{status}")
    public ResponseEntity<?> getDeliveriesAtSameBoxOfCustomerByStatus(@PathVariable String customerID, @PathVariable String boxID, @PathVariable String status) throws Exception {
        return ResponseEntity.ok(deliveryService.getDeliveriesAtSameBoxOfCustomerByStatus(customerID, boxID, status));
    }

    @GetMapping("/deliverer/{delivererID}/box/{boxID}/status/{status}")
    public ResponseEntity<?> getDeliveriesAtSameBoxOfCustomerByStatus(@PathVariable String delivererID, @PathVariable String boxID, @PathVariable String status) throws Exception {
        return ResponseEntity.ok(deliveryService.getDeliveriesAtSameBoxOfCustomerByStatus(delivererID, boxID, status));
    }

}
