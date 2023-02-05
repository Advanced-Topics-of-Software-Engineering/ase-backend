package edu.tum.ase.ase23.controller;

import edu.tum.ase.ase23.model.Box;
import edu.tum.ase.ase23.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/box")
public class BoxController {

    @Autowired
    BoxService boxService;

    @GetMapping("")
    public ResponseEntity<?> getAllBoxes() {
        return ResponseEntity.ok(boxService.getAllBoxes());
    }

    @PostMapping("")
    public ResponseEntity<?> createBox(@RequestBody Box box) {
        return ResponseEntity.ok(boxService.createBox(box));
    }

    @GetMapping("/name/{Name}")
    public ResponseEntity<?> findBoxByName(@PathVariable String Name) throws Exception {
        return ResponseEntity.ok(boxService.findByName(Name));
    }

    @GetMapping("/address/{StreetAddress}")
    public ResponseEntity<?> findBoxByStreetAddress(@PathVariable String StreetAddress) throws Exception {
        return ResponseEntity.ok(boxService.findByStreetAddress(StreetAddress));
    }

    @GetMapping("/{Id}")
    public ResponseEntity<?> findBoxById(@PathVariable String Id) throws Exception {
        return ResponseEntity.ok(boxService.findById(Id));
    }

    @PostMapping("/update/{Id}")
    public ResponseEntity<?> updateBox(@PathVariable String Id, @RequestBody Box box) throws Exception {
        Box updatedBox = boxService.update(Id, box);
        return ResponseEntity.ok(updatedBox);
    }

    @PostMapping("/delete/{Id}")
    public ResponseEntity<?> deleteBox(@PathVariable String Id) throws Exception {
        if (!boxService.delete(Id)) {
            return ResponseEntity
                    .badRequest()
                    .body("Could not delete: All deliveries of this box is not completed!");
        }
        return ResponseEntity.ok("Deleted box with " + Id + " successfully");
    }

}