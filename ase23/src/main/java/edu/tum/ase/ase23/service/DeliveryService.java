package edu.tum.ase.ase23.service;


import edu.tum.ase.ase23.model.Delivery;
import edu.tum.ase.ase23.model.User;
import edu.tum.ase.ase23.repository.DeliveryRepository;
import edu.tum.ase.ase23.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepo;
    @Autowired
    UserService userService;

    public List<Delivery> getAllDeliveries() {
        return deliveryRepo.findAll();
    }

    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepo.save(delivery);
    }

    public Delivery getDeliveriesOfUserFromUserId(String userId) throws Exception {
        User user = userService.getUserById(userId);
        System.out.println("User:" + user);
        List<Delivery> deliveries = this.getAllDeliveries();
        return deliveries.stream().filter(delivery ->
                        delivery.getCustomer().getId().equals(user.getId()) ||
                                delivery.getDeliverer().getId().equals(user.getId())).findFirst()
                .orElse(null);
    }
}

