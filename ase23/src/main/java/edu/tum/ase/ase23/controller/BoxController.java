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
    public List<Box> getAllProject() {
        return boxService.getAllBoxes();
    }

    @PostMapping("")
    public Box createBox(@RequestBody Box box) {
        return boxService.createBox(box);
    }

    @GetMapping("/{boxName}")
    public Box findProjectByName(@PathVariable String boxName) throws Exception {
        return boxService.findByName(boxName);
    }
}