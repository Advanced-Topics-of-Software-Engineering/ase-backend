package edu.tum.ase.ase23.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Authenticator;
import edu.tum.ase.ase23.model.Delivery;
import edu.tum.ase.ase23.model.RoleEnum;
import edu.tum.ase.ase23.model.User;
import edu.tum.ase.ase23.payload.request.BoxRequest;
import edu.tum.ase.ase23.payload.request.DeliveryCreateRequest;
import edu.tum.ase.ase23.payload.response.MessageResponse;
import edu.tum.ase.ase23.repository.DeliveryRepository;
import edu.tum.ase.ase23.service.DeliveryService;
import edu.tum.ase.ase23.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
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
    public ResponseEntity<?> createDelivery(@RequestBody DeliveryCreateRequest deliveryCreateRequest) throws Exception {
        deliveryService.createDelivery(deliveryCreateRequest);
        return ResponseEntity.ok(new MessageResponse("Success: Delivery created!"));
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
    public ResponseEntity<?> getDeliveryByTrackingID(@PathVariable String trackingID, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String body = request.getAttribute("body").toString().replace("=",":");
        JSONObject bodyContentJson = new JSONObject(body);
        String userId = bodyContentJson.get("id").toString();

        String userRole = bodyContentJson.get("authorities").toString()
                .substring(15,bodyContentJson.get("authorities").toString().length()-3);


        Delivery delivery = deliveryService.getDeliveryByTrackingID(trackingID);
        if (RoleEnum.valueOf(userRole).equals(RoleEnum.ROLE_CUSTOMER)) {
            if (userId.equals(delivery.getCustomerID())) {
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(delivery);
            }
        }
        else if (RoleEnum.valueOf(userRole).equals(RoleEnum.ROLE_DELIVERER)) {
            if (userId.equals(delivery.getDelivererID())) {
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(delivery);
            }
        }
        else if (RoleEnum.valueOf(userRole).equals(RoleEnum.ROLE_DISPATCHER)) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(delivery);
        }
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MessageResponse("Error: Something went wrong!"));

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

    // Update Delivery Status from ORDERED to PICKEDUP
    @GetMapping("deliverer/updateStatus/{trackingID}")
    public ResponseEntity<?> updateStatusToPickedUpByTrackingID(@PathVariable String trackingID) throws Exception {
        Delivery delivery = deliveryService.getDeliveryByTrackingID(trackingID);
        String delivererID = delivery.getDelivererID();
        String currentDelivererID = "63dbeaae6ad1cc79825978e5"; //will be deleted
        // current_delivererID = request.header.userid.
        if (currentDelivererID.equals(delivererID)) {
            if (delivery.getStatus().equals("ORDERED")) {
                delivery.setStatus("PICKEDUP");
                return new ResponseEntity<>("Delivery with tracking ID : " + trackingID + "is picked-up" , HttpStatus.OK);
            }
        }
        return ResponseEntity.badRequest().body("You have scanned the wrong box!");
    }

    // Box validation to open with RFID Token by User type
    @PostMapping("/box/validate")
    public ResponseEntity<?> updateBoxValidation(@Valid @RequestBody BoxRequest boxRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.convertValue(request.getAttribute("body"), User.class);
//            User user = (User) request.getAttribute("body");
//            User user = userService.getUserByRFIDToken(RFIDToken);
            String userID = user.getId();
            //ADD There is no user return bad request unauthorized user
            String UserType = user.getRoles().stream().findAny().get().getRoleEnum().toString();
            if (UserType.equals("ROLE_DELIVERER")) {
                List<Delivery> pickedUpDeliveries = deliveryService.getDeliveriesOfDelivererByStatus(userID, boxRequest.getBoxId(), "PICKEDUP");
                if (!pickedUpDeliveries.isEmpty()) {
                    List<String> DelivererIDsOfDeliveries = pickedUpDeliveries.stream().map(delivery -> delivery.getId()).toList();
                    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(DelivererIDsOfDeliveries);
                }
            } else if (UserType.equals("ROLE_CUSTOMER")) {
                List<Delivery> deliveredDeliveries = deliveryService.getDeliveriesOfCustomerByStatus(userID, boxRequest.getBoxId(), "DELIVERED");
                if (!deliveredDeliveries.isEmpty()) {
                    List<String> CustomerIDsOfDeliveries = deliveredDeliveries.stream().map(delivery -> delivery.getId()).toList();
                    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(CustomerIDsOfDeliveries);
                }
            }
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(new MessageResponse("Error: Something went wrong!"));

        } catch (Exception e) {
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(new MessageResponse(e.getMessage()));
        }

    }

    // After closing Box, update status of deliveries
    @PostMapping("/box/close/{boxID}")
    public ResponseEntity<?> updateStatusOfDeliveries(@PathVariable String boxID) throws Exception {
        //List<Delivery> valid_deliveries = deliveryService.getDeliveriesFromBoxId(boxID).stream().filter(delivery -> delivery.getBox().getId().equals(boxID)).collect(Collectors.toList());
        List<Delivery> pickedUpDeliveries = deliveryService.getDeliveriesFromBoxId(boxID).stream().filter(delivery -> delivery.getStatus().equals("PICKEDUP")).collect(Collectors.toList());
        List<Delivery> deliveredDeliveries = deliveryService.getDeliveriesFromBoxId(boxID).stream().filter(delivery -> delivery.getStatus().equals("DELIVERED")).collect(Collectors.toList());

        if (!pickedUpDeliveries.isEmpty()){
            pickedUpDeliveries.stream().forEach(delivery ->
            {
                delivery.setStatus("DELIVERED");
                deliveryRepository.save(delivery);
            });
            return ResponseEntity.ok("Your deliveries are delivered at box with boxID: " + boxID);
        }


        else if (!deliveredDeliveries.isEmpty()){
            deliveredDeliveries.stream().forEach(delivery ->
            {
                delivery.setStatus("COMPLETED");
                deliveryRepository.save(delivery);
            });
            return ResponseEntity.ok("All your deliveries are completed");
        }
        else{
            return ResponseEntity.ok("");
        }
    }
    @GetMapping("/delete/{deliveryID}")
    public ResponseEntity<?> deleteDelivery(@PathVariable String deliveryID) throws Exception {
        if (!deliveryService.delete(deliveryID)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Could not delete, make sure that there is a delivery with ID: " + deliveryID) );
        }
        return ResponseEntity.ok(new MessageResponse("Success: Deleted delivery with id " + deliveryID + "!"));
    }
}




