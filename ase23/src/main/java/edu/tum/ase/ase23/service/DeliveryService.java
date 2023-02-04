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

    public List<Delivery> getDeliveriesOfUserFromDelivererId(String delivererId) throws Exception {
        User user = userService.getUserById(delivererId);
        List<Delivery> deliveries = this.getAllDeliveries();
        return deliveries.stream().filter(delivery ->
                delivery.getDelivererID().equals(user.getId())).collect(Collectors.toList());
    }

    public List<Delivery> getDeliveriesFromBoxId(String boxId) throws Exception {
        if (boxId == null || boxId.isEmpty()) {
            throw new Exception("Box ID is required");
        }
        List<Delivery> deliveries = this.getAllDeliveries();
        return deliveries.stream().filter(delivery ->
                delivery.getBox().getId().equals(boxId)).collect(Collectors.toList());
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

    public Delivery updateDeliveryByDeliveryID(String deliveryID, Delivery delivery) throws Exception {
        if (deliveryID == null || deliveryID.isEmpty()) {
            throw new Exception("Delivery ID is required");
        }
        Delivery updatedDelivery = this.getDeliveryById(deliveryID);
        if (delivery.getBox() != null) {
            Box updatedBox = boxService.update(delivery.getBox().getId(), delivery.getBox());
            updatedDelivery.setBox(updatedBox);
        }
        if (delivery.getCustomerID() != null && !delivery.getCustomerID().isEmpty()){
            updatedDelivery.setCustomerID(delivery.getCustomerID());
        }
        if (delivery.getDelivererID() != null && !delivery.getDelivererID().isEmpty()){
            updatedDelivery.setDelivererID(delivery.getDelivererID());
        }
        if (delivery.getStatus() != null  && !delivery.getStatus().isEmpty()) {
            updatedDelivery.setStatus(delivery.getStatus());
        }
        if (delivery.getTrackingID() != null && !delivery.getStatus().isEmpty()) {
            updatedDelivery.setTrackingID(delivery.getTrackingID());
        }
        return deliveryRepository.save(updatedDelivery);
    }

    public List<Delivery> getDeliveriesOfCustomerByBoxID(String deliveryID, String boxID) throws Exception {
        List<Delivery> deliveriesOfCustomer = this.getDeliveriesOfUserFromDelivererId(deliveryID);
        // Filter those deliveries by boxID and return all matching that boxID

    }
}

