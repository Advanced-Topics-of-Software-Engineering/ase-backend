package edu.tum.ase.ase23.controller;

import edu.tum.ase.ase23.model.Box;
import edu.tum.ase.ase23.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/name/{boxName}")
    public Box findBoxByName(@PathVariable String boxName) throws Exception {
        return boxService.findByName(boxName);
    }

    @GetMapping("/address/{boxStreetAddress}")
    public Box findBoxByStreetAddress(@PathVariable String StreetAddress) throws Exception {
        return boxService.findByStreetAddress(StreetAddress);
    }

    @GetMapping("/status/{boxAlive}")
    public Box findBoxByAlive(@PathVariable Boolean Alive) throws Exception {
        return boxService.findByAlive(Alive);
    }

}