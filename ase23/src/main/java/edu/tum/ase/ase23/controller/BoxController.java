package edu.tum.ase.ase23.controller;

import edu.tum.ase.ase23.model.Box;
import edu.tum.ase.ase23.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.lang.Boolean.parseBoolean;

@RestController
@RequestMapping("/box")
public class BoxController {

    @Autowired
    BoxService boxService;

    @GetMapping("")
    public List<Box> getAllBoxes() {
        return boxService.getAllBoxes();
    }

    @PostMapping("")
    public Box createBox(@RequestBody Box box) {
        return boxService.createBox(box);
    }

    @GetMapping("/name/{Name}")
    public Box findBoxByName(@PathVariable String Name) throws Exception {
        return boxService.findByName(Name);
    }

    @GetMapping("/address/{StreetAddress}")
    public Box findBoxByStreetAddress(@PathVariable String StreetAddress) throws Exception {
        return boxService.findByStreetAddress(StreetAddress);
    }

    @GetMapping("/status/{Alive}")
    public List<Box> findBoxAllAlive(@PathVariable Boolean Alive) throws Exception {
        return boxService.findAllAlive(Alive);
    }

    @GetMapping("/{Id}")
    public Box findBoxById(@PathVariable String Id) throws Exception {
        return boxService.findById(Id);
    }

    @PostMapping("/update/{Id}")
    public ResponseEntity<Box> updateBox(@PathVariable String Id, @RequestBody Box box) throws Exception {
        Box updatedBox = boxService.update(Id, box);
        return new ResponseEntity<>(updatedBox, HttpStatus.OK);
    }

    @PostMapping("/delete/{Id}")
    public ResponseEntity<Box> deleteBox(@PathVariable String Id) throws Exception {
        Box deletedBox = boxService.delete(Id);
        return new ResponseEntity<>(deletedBox, HttpStatus.OK);
    }

}