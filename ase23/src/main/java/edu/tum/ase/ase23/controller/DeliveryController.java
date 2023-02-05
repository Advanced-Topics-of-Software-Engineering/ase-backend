package edu.tum.ase.ase23.controller;

import com.sun.net.httpserver.Authenticator;
import edu.tum.ase.ase23.model.Delivery;
import edu.tum.ase.ase23.model.User;
import edu.tum.ase.ase23.payload.request.UpdateStatusDto;
import edu.tum.ase.ase23.repository.DeliveryRepository;
import edu.tum.ase.ase23.service.DeliveryService;
import edu.tum.ase.ase23.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    DeliveryService deliveryService;

    @Autowired
    UserService userService;

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
        if (deliveryService.getDeliveriesOfUserFromDelivererId(delivererId).equals(null)
                || deliveryService.getDeliveriesOfUserFromDelivererId(delivererId).isEmpty()) {
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body("{Error: There is no delivery to deliverer with " + delivererId + "}");
        }
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
        if(deliveryService.getDeliveriesOfCustomerByBoxID(customerID, boxID) != null){
            return ResponseEntity.ok(deliveryService.getDeliveriesOfCustomerByBoxID(customerID, boxID));
        }
        else{
            return ResponseEntity.badRequest().body("customerId or BoxId cannot be null");
        }

    }

    // Get deliveries of deliverer by box id
    @GetMapping("/deliverer/{delivererID}/box/{boxID}")
    public ResponseEntity<?> getDeliveriesOfDelivererByBoxID(@PathVariable String delivererID, @PathVariable String boxID) throws Exception {
        if(deliveryService.getDeliveriesOfDelivererByBoxID(delivererID, boxID) != null){
            return ResponseEntity.ok(deliveryService.getDeliveriesOfDelivererByBoxID(delivererID, boxID));
        }
        else{
            return ResponseEntity.badRequest().body("DelivererID or BoxId cannot be null");

        }
    }

    // Update Delivery
    @PostMapping("/update/{deliveryID}")
    public ResponseEntity<?> updateDeliveryByDeliveryID(@PathVariable String deliveryID, @RequestBody Delivery delivery) throws Exception {
        return ResponseEntity.ok(deliveryService.updateDeliveryByDeliveryID(deliveryID, delivery));
    }

    // Update Delivery Status from Ordered to PickedUp
    @GetMapping("deliverer/updateStatus/{trackingID}")
    public ResponseEntity<?> updateStatusToPickedUpByTrackingID(@PathVariable String trackingID) throws Exception {
        Delivery delivery = deliveryService.getDeliveryByTrackingID(trackingID);
        String delivererID = delivery.getDelivererID();
        String currentDelivererID = "63dbeaae6ad1cc79825978e5"; //will be deleted
        // current_delivererID = request.header.userid.
        if (currentDelivererID.equals(delivererID)) {
            if (delivery.getStatus().equals("Ordered")) {
                delivery.setStatus("PickedUp");
                return new ResponseEntity<>("Delivery with tracking ID : " + trackingID + "is picked-up" , HttpStatus.OK);
            }
        }
        return ResponseEntity.badRequest().body("You have scanned the wrong box!");
    }

    // Box validation to open with RFID Token by User type
    @PostMapping("/box/validate/{RFIDToken}/{BoxID}")
    public ResponseEntity<?> updateBoxValidation(@PathVariable String RFIDToken, @PathVariable String BoxID) throws Exception {
        User user = this.userService.getUserByRFIDToken(RFIDToken);
        String userID = user.getId();
        //ADD There is no user return bad request unauthorized user
        String UserType = user.getRoles().stream().findAny().get().getRoleEnum().toString();
        if (UserType.equals("ROLE_DELIVERER")) {
            List<Delivery> pickedUpDeliveries = this.deliveryService.getDeliveriesOfDelivererByStatus(userID, BoxID, "PickedUp");
            if (!pickedUpDeliveries.isEmpty()) {
                List <String> DelivererIDsOfDeliveries = new ArrayList<String>();
                pickedUpDeliveries.forEach( delivery -> DelivererIDsOfDeliveries.add(delivery.getId()));
                return ResponseEntity.ok(DelivererIDsOfDeliveries);
            }

        }
        else if (UserType.equals("ROLE_CUSTOMER")) {
            List<Delivery> deliveredDeliveries = this.deliveryService.getDeliveriesOfCustomerByStatus(userID, BoxID, "Delivered");
            if (!deliveredDeliveries.isEmpty()) {
                List <String> CustomerIDsOfDeliveries = new ArrayList<String>();
                deliveredDeliveries.forEach( delivery -> CustomerIDsOfDeliveries.add(delivery.getId()));
                return ResponseEntity.ok(CustomerIDsOfDeliveries);
            }
        }
        return null; //????

    }

    // After closing Box, update status of deliveries
    @PostMapping("/box/close/{boxID}")
    public ResponseEntity<?> updateStatusOfDeliveries(@PathVariable String boxID) throws Exception {
        //List<Delivery> valid_deliveries = deliveryService.getDeliveriesFromBoxId(boxID).stream().filter(delivery -> delivery.getBox().getId().equals(boxID)).collect(Collectors.toList());
        List<Delivery> pickedUp_deliveries = deliveryService.getDeliveriesFromBoxId(boxID).stream().filter(delivery -> delivery.getStatus().equals("PickedUp")).collect(Collectors.toList());
        List<Delivery> delivered_deliveries = deliveryService.getDeliveriesFromBoxId(boxID).stream().filter(delivery -> delivery.getStatus().equals("Delivered")).collect(Collectors.toList());

        if (!pickedUp_deliveries.isEmpty()){
            pickedUp_deliveries.stream().forEach(delivery ->
            {
                delivery.setStatus("Delivered");
                deliveryRepository.save(delivery);
            });
            return ResponseEntity.ok("Your deliveries are delivered at box with boxID: " + boxID);
        }


        if (!pickedUp_deliveries.isEmpty()){
            pickedUp_deliveries.stream().forEach(delivery ->
            {
                delivery.setStatus("Delivered");
                deliveryRepository.save(delivery);
            });
            return ResponseEntity.ok("Your deliveries are delivered at box with boxID: " + boxID);
        }
        else if (!delivered_deliveries.isEmpty()){
            delivered_deliveries.stream().forEach(delivery ->
            {
                delivery.setStatus("Completed");
                deliveryRepository.save(delivery);
            });
            return ResponseEntity.ok("All your deliveries are completed");
        }
        else{
            return ResponseEntity.ok("");
        }
    }
    @PostMapping("/delete/{deliveryID}")
    public ResponseEntity<?> deleteDelivery(@PathVariable String deliveryID) throws Exception {
        if (!deliveryService.delete(deliveryID)) {
            return ResponseEntity
                    .badRequest()
                    .body("Could not delete, make sure that there is a delivery with ID: " + deliveryID);
        }
        return ResponseEntity.ok("Deleted delivery with id " + deliveryID + " successfully");
    }
}




