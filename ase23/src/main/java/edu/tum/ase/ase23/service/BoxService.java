package edu.tum.ase.ase23.service;

import edu.tum.ase.ase23.model.Box;
import edu.tum.ase.ase23.repository.BoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoxService {
    @Autowired
    BoxRepository boxRepository;

    public List<Box> getAllBoxes() {
        return boxRepository.findAll();
    }

    public Box createBox(Box box) {
        return boxRepository.save(box);
    }

    public Box findById(String Id) throws Exception {
        return boxRepository.findById(Id).orElseThrow(() -> new Exception("No Box with this ID " + Id));
    }

    public Box findByName(String Name) throws Exception {
        return boxRepository.findByName(Name).orElseThrow(() -> new Exception("No Box with name " + Name));
    }

    public Box findByStreetAddress(String StreetAddress) throws Exception {
        return boxRepository.findByStreetAddress(StreetAddress).orElseThrow(() -> new Exception("No Box with this Street Address " + StreetAddress));
    }

    public List<Box> findAllAlive(Boolean Alive) throws Exception {
        List<Box> boxes = this.getAllBoxes();
        return boxes.stream().filter(box -> box.getAlive().equals(Alive)).collect(Collectors.toList());
    }



    public Box update(String Id, Box box) throws Exception {
        Box existingBox = boxRepository.findById(Id).orElseThrow(() -> new Exception("Box not found with id: " + Id));
        existingBox.setName(box.getName());
        existingBox.setStreetAddress(box.getStreetAddress());
        existingBox.setAlive(box.getAlive());
        return boxRepository.save(existingBox);
    }

    public Box delete(String Id) throws Exception {
        Box deletedBox = boxRepository.findById(Id).orElseThrow(() -> new Exception("Box not found with id: " + Id));
        deletedBox.setAlive(false);
        return boxRepository.save(deletedBox);
    }
}
