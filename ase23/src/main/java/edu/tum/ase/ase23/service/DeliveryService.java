package edu.tum.ase.ase23.service;


import edu.tum.ase.ase23.model.Box;
import edu.tum.ase.ase23.model.Delivery;
import edu.tum.ase.ase23.model.User;
import edu.tum.ase.ase23.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    UserService userService;
    @Autowired
    BoxService boxService;

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> getDeliveriesOfUserFromCustomerId(String customerId) throws Exception {
        User user = userService.getUserById(customerId);
        List<Delivery> deliveries = this.getAllDeliveries();
        return deliveries.stream().filter(delivery ->
                        delivery.getCustomerID().equals(user.getId())).collect(Collectors.toList());
    }

    public List<Delivery> getDeliveriesOfUserFromDeliveryId(String delivererId) throws Exception {
        User user = userService.getUserById(delivererId);
        List<Delivery> deliveries = this.getAllDeliveries();
        return deliveries.stream().filter(delivery ->
                        delivery.getDelivererID().equals(user.getId())).collect(Collectors.toList());
    }

    public Delivery getDeliveryById(String deliveryId) throws Exception {
        if (deliveryId == null || deliveryId.isEmpty()) {
            throw new Exception("Delivery ID is required");
        }
        List<Delivery> deliveries = this.getAllDeliveries();
        Delivery matchedDelivery = deliveries.stream().filter(delivery ->
                delivery.getId().equals(deliveryId)).findFirst().orElse(null);
        return matchedDelivery;
    }
    public Delivery getDeliveryByTrackingID(String trackingID) throws Exception {
        if (trackingID == null || trackingID.isEmpty()) {
            throw new Exception("Tracking ID is required");
        }
        List<Delivery> deliveries = this.getAllDeliveries();
        Delivery matchedDelivery = deliveries.stream().filter(delivery ->
                delivery.getTrackingID().equals(trackingID)).findFirst().orElse(null);
        return matchedDelivery;
    }


    public List<Delivery> getDeliveriesFromBoxId(String boxId) throws Exception {
        if (boxId == null || boxId.isEmpty()) {
            throw new Exception("Box ID is required");
        }
        List<Delivery> deliveries = this.getAllDeliveries();
        List<Delivery> matchedDeliveries = deliveries.stream().filter(delivery ->
                delivery.getBox().getId().equals(boxId)).collect(Collectors.toList());
        return matchedDeliveries;
    }

    public List<Delivery> getCompletedDeliveriesOfBox(String boxID) throws Exception {
        if (boxID == null || boxID.isEmpty()) {
            throw new Exception("Box ID is required");
        }
        // If all the deliveries of this box is completed,
        // Assign all these deliveries to deletedBox,
        // Then delete this box.
        // Else, throw an error.
        List<Delivery> deliveries = this.getDeliveriesFromBoxId(boxID);
        List<Delivery> completedDeliveries = deliveries.stream().filter(delivery ->
                delivery.getStatus().equals("Completed")).toList();
        if (completedDeliveries.isEmpty()) { // There exist incomplete boxes still
            return List.of();
        }
        if (deliveries.size() > completedDeliveries.size()) { // Not every box is completed
            return List.of();
        }
        return completedDeliveries;
    }

    public void assignGarbageBoxToCompletedDeliveries(List<Delivery> completedDeliveries) throws Exception {
        String garbageBoxName = "DELETED_BOX";
        Box garbageBox = boxService.findByName(garbageBoxName);
        completedDeliveries.forEach(delivery ->
                {
                    try {
                        this.updateBoxOfDelivery(
                                    delivery.getId(),
                                    garbageBox);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

        );
    }
    public void updateBoxOfDelivery(String deliveryID, Box box) throws Exception {
        if (deliveryID == null || deliveryID.isEmpty()) {
            throw new Exception("Delivery ID is required");
        }
        if (box.getId() == null) {
            throw new Exception("Box ID is required");
        }
        Delivery updatedDelivery = this.getDeliveryById(deliveryID);
        updatedDelivery.setBox(box);
        deliveryRepository.save(updatedDelivery);
    }

    public Delivery updateDeliveryByDeliveryID(String deliveryID, Delivery delivery) throws Exception {
        if (deliveryID == null || deliveryID.isEmpty()) {
            throw new Exception("Delivery ID is required");
        }
        Delivery updatedDelivery = this.getDeliveryById(deliveryID);
        if (delivery.getBox() != null) {
            Box updatedBox = boxService.update(delivery.getBox().getId(), delivery.getBox());
            updatedDelivery.setBox(updatedBox);
        }
        if (delivery.getCustomerID() != null && !delivery.getCustomerID().isEmpty()) {
            updatedDelivery.setCustomerID(delivery.getCustomerID());
        }
        if (delivery.getDelivererID() != null && !delivery.getDelivererID().isEmpty()) {
            updatedDelivery.setDelivererID(delivery.getDelivererID());
        }
        if (delivery.getStatus() != null && !delivery.getStatus().isEmpty()) {
            updatedDelivery.setStatus(delivery.getStatus());
        }
        if (delivery.getTrackingID() != null && !delivery.getStatus().isEmpty()) {
            updatedDelivery.setTrackingID(delivery.getTrackingID());
        }
        return deliveryRepository.save(updatedDelivery);
    }
}

