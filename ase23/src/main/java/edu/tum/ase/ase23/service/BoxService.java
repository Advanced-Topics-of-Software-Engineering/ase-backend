package edu.tum.ase.ase23.service;

import edu.tum.ase.ase23.model.Box;
import edu.tum.ase.ase23.repository.BoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxService {
    @Autowired
    BoxRepository boxRepo;

    public List<Box> getAllBoxes() {
        return boxRepo.findAll();
    }

    public Box createBox(Box box) {
        return boxRepo.save(box);
    }

    public Box findByName(String name) throws Exception {
        return boxRepo.findByName(name).orElseThrow(() -> new Exception("No Box with name " + name));
    }

    public Box findByStreetAddress(String streetAddress) throws Exception {
        return boxRepo.findByStreetAddress(streetAddress).orElseThrow(() -> new Exception("No Box with this Street Address " + streetAddress));
    }

    public Box findByAlive(Boolean alive) throws Exception {
        return boxRepo.findByAlive(alive).orElseThrow(() -> new Exception("There is no any alive box!"));
    }

    
}
