package edu.tum.ase.ase23.controller;

import edu.tum.ase.ase23.model.Delivery;
import edu.tum.ase.ase23.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

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
        return ResponseEntity.ok(deliveryService.getDeliveriesOfUserFromDeliveryId(delivererId));
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

    // Update Delivery
    @PostMapping("/update/{deliveryID}")
    public ResponseEntity<?> updateDeliveryByDeliveryID(@PathVariable String deliveryID, @RequestBody Delivery delivery) throws Exception {
        return ResponseEntity.ok(deliveryService.updateDeliveryByDeliveryID(deliveryID, delivery));
    }

    @PostMapping("/delete/{deliveryID}")
    public ResponseEntity<?> deleteDelivery(@PathVariable String deliveryID) throws Exception {
        if (!deliveryService.delete(deliveryID)) {
            return ResponseEntity
                    .badRequest()
                    .body("Could not delete");
        }
        return ResponseEntity.ok("Deleted delivery with id " + deliveryID + " successfully");
    }

}
