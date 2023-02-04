package edu.tum.ase.ase23.service;

import edu.tum.ase.ase23.model.Box;
import edu.tum.ase.ase23.model.Delivery;
import edu.tum.ase.ase23.repository.BoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxService {
    @Autowired
    BoxRepository boxRepository;

    @Autowired
    @Lazy
    DeliveryService deliveryService;
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

    public Box update(String Id, Box box) throws Exception {
        Box existingBox = boxRepository.findById(Id).orElseThrow(() -> new Exception("Box not found with id: " + Id));
        if(box.getName() != null){
            existingBox.setName(box.getName());
        }
        if(box.getStreetAddress() != null){
            existingBox.setStreetAddress(box.getStreetAddress());
        }
        return boxRepository.save(existingBox);
    }

    public Boolean delete(String Id) throws Exception {
        Box boxToDelete = boxRepository.findById(Id).orElseThrow(() -> new Exception("Box not found with id: " + Id));
        // If there is no delivery related to this box, delete box directly
        if (deliveryService.getDeliveriesFromBoxId(Id).isEmpty()){
            boxRepository.delete(boxToDelete);
            return Boolean.TRUE;
        }
        List<Delivery> completedDeliveriesOfBox = deliveryService.getCompletedDeliveriesOfBox(Id);
        if (completedDeliveriesOfBox.isEmpty()) {
            return Boolean.FALSE;
        }
        deliveryService.assignGarbageBoxToCompletedDeliveries(completedDeliveriesOfBox);
        boxRepository.delete(boxToDelete);
        return Boolean.TRUE;
    }

}
