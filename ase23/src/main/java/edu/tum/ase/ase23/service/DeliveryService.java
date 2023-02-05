package edu.tum.ase.ase23.service;


import edu.tum.ase.ase23.model.Box;
import edu.tum.ase.ase23.model.Delivery;
import edu.tum.ase.ase23.model.User;
import edu.tum.ase.ase23.payload.request.DeliveryCreateRequest;
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

    public Delivery createDelivery(DeliveryCreateRequest delivery) throws Exception {
        Box box = boxService.findById(delivery.getBoxID());
        Delivery newDelivery = new Delivery(box, delivery.getCustomerID(), delivery.getDelivererID());
        return deliveryRepository.save(newDelivery);
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
                delivery.getStatus().equals("COMPLETED")).toList();
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

        return deliveryRepository.save(updatedDelivery);
    }

    public List<Delivery> getDeliveriesOfCustomerByBoxID(String customerID, String boxID) throws Exception {
        if (customerID == null || customerID.isEmpty() || boxID == null || boxID.isEmpty()) {
            return null;
        }
        List<Delivery> deliveriesOfCustomer = this.getDeliveriesOfUserFromCustomerId(customerID);
        List<Delivery> deliveriesOfCustomerAtSameBox = deliveriesOfCustomer.stream().filter(delivery ->
                delivery.getBox().getId().equals(boxID)).collect(Collectors.toList());
        return deliveriesOfCustomerAtSameBox;
        // Filter deliveries of the customer by boxID and return all deliveries of user at the same box.
    }

    public List<Delivery> getDeliveriesOfDelivererByBoxID(String delivererID, String boxID) throws Exception {
        if (delivererID == null || delivererID.isEmpty() || boxID == null || boxID.isEmpty()) {
            return null;
        }
        List<Delivery> deliveriesOfDeliverer = this.getDeliveriesOfUserFromDelivererId(delivererID);
        List<Delivery> deliveriesOfDelivererAtSameBox = deliveriesOfDeliverer.stream().filter(delivery ->
                delivery.getBox().getId().equals(boxID)).collect(Collectors.toList());
        return deliveriesOfDelivererAtSameBox;
        // Filter deliveries of the deliverer by boxID and return all deliveries of user at the same box.
    }

    public List<Delivery> getDeliveriesOfCustomerByStatus(String customerID, String boxID, String status) throws Exception {
        List<Delivery> deliveriesOfCustomerAtSameBox = this.getDeliveriesOfCustomerByBoxID(customerID, boxID);
        List<Delivery> deliveriesOfCustomerAtSameBoxAsStatus = deliveriesOfCustomerAtSameBox.stream().filter(delivery ->
                delivery.getStatus().equals(status)).collect(Collectors.toList());
        return deliveriesOfCustomerAtSameBoxAsStatus;
        // Filter deliveries of the customer by boxID and return all deliveries of user at the same box as Box Status.
    }

    public List<Delivery> getDeliveriesOfDelivererByStatus(String delivererID, String boxID, String status) throws Exception {
        List<Delivery> deliveriesOfDelivererAtSameBox = this.getDeliveriesOfDelivererByBoxID(delivererID, boxID);
        List <Delivery> deliveriesOfDelivererAtSameBoxAsStatus = deliveriesOfDelivererAtSameBox.stream().filter(delivery ->
                delivery.getStatus().equals(status)).collect(Collectors.toList());
        return deliveriesOfDelivererAtSameBoxAsStatus;
        // Filter deliveries of the deliverer by boxID and return all deliveries of user at the same box as box status.
    }

    public Boolean delete(String deliveryID) throws Exception {
        Delivery deliveryToDelete;
        try {
            deliveryToDelete = deliveryRepository.findById(deliveryID).orElseThrow(() -> new Exception("Delivery not found with id: " + deliveryID));
        } catch (Exception e) {
            return Boolean.FALSE;
        }
        deliveryRepository.delete(deliveryToDelete);
        return Boolean.TRUE;
    }
}

