package edu.tum.ase.box.service;

import edu.tum.ase.box.model.Box;
import edu.tum.ase.box.repository.BoxRepository;
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

}
