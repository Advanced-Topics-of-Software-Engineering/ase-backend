package edu.tum.ase.ase23.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tum.ase.ase23.model.Box;
import edu.tum.ase.ase23.model.Delivery;
import edu.tum.ase.ase23.payload.response.MessageResponse;
import edu.tum.ase.ase23.service.BoxService;
import edu.tum.ase.ase23.service.DeliveryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/box")
public class BoxController {

    @Autowired
    BoxService boxService;

    @Autowired
    DeliveryService deliveryService;

    @GetMapping("")
    public ResponseEntity<?> getAllBoxes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseEntity res = ResponseEntity.ok().body(boxService.getAllBoxes());
        return res;
    }

    @PostMapping("")
    public ResponseEntity<?> createBox(@RequestBody Box box) {
        boxService.createBox(box);
        return ResponseEntity.ok().body(new MessageResponse("Success: Box has been created!"));
    }

    @GetMapping("/name/{Name}")
    public ResponseEntity<?> findBoxByName(@PathVariable String Name) throws Exception {
        return ResponseEntity.ok().body(boxService.findByName(Name));
    }

    @GetMapping("/address/{StreetAddress}")
    public ResponseEntity<?> findBoxByStreetAddress(@PathVariable String StreetAddress) throws Exception {
        return ResponseEntity.ok().body(boxService.findByStreetAddress(StreetAddress));
    }

    @GetMapping("/{Id}")
    public ResponseEntity<?> findBoxById(@PathVariable String Id) throws Exception {
        return ResponseEntity.ok().body(boxService.findById(Id));
    }

    @PostMapping("/update/{Id}")
    public ResponseEntity<?> updateBox(@PathVariable String Id, @RequestBody Box box) throws Exception {
        Box updatedBox = boxService.update(Id, box);
        return ResponseEntity.ok().body(new MessageResponse("Success: Box has been updated!"));
    }

    @PostMapping("/delete/{Id}")
    public ResponseEntity<?> deleteBox(@PathVariable String Id) throws Exception {
        if (!boxService.delete(Id)) {
            return ResponseEntity
                    .badRequest()
                    .body("Could not delete: All deliveries of this box is not completed!");
        }
        return ResponseEntity.ok().body(new MessageResponse("Success: Box with id " + Id + "has been deleted!"));
    }

    @GetMapping("/available/{userid}")
    public ResponseEntity<?> getAvailableBoxesForCustomer(@PathVariable String userid) {
        List<String> impossibleBoxes = deliveryService.getAllDeliveries()
                .stream()
                .filter(delivery -> !delivery.getStatus().toUpperCase().equals("COMPLETED"))
                .filter(delivery -> !delivery.getCustomerID().equals(userid))
                .map(delivery -> delivery.getBox().getId())
                .toList();

        List<Box> allBoxes = boxService.getAllBoxes();
        List<Box> possibleBoxes = allBoxes.stream()
                .filter(box -> !impossibleBoxes.contains(box.getId()))
                .toList();

        return ResponseEntity.ok().body(possibleBoxes);
    }
}