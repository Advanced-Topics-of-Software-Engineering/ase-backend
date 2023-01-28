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

    public List<Box> findAllAlive(Boolean Alive) throws Exception {
        List<Box> boxes = this.getAllBoxes();
        return boxes.stream().filter(box -> box.getAlive().equals(Alive)).collect(Collectors.toList());
    }



    public Box update(String Id, Box box) throws Exception {
        Box existingBox = boxRepository.findById(Id).orElseThrow(() -> new Exception("Box not found with id: " + Id));
        if(box.getName() != null){
            existingBox.setName(box.getName());
        }
        if(box.getStreetAddress() != null){
            existingBox.setStreetAddress(box.getStreetAddress());
        }
        if(box.getAlive() != null){
            existingBox.setAlive(box.getAlive());
        }

        return boxRepository.save(existingBox);
    }

    public Box delete(String Id) throws Exception {
        Box deletedBox = boxRepository.findById(Id).orElseThrow(() -> new Exception("Box not found with id: " + Id));
        deletedBox.setAlive(false);
        return boxRepository.save(deletedBox);
    }
}
