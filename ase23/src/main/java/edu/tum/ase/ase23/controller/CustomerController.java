package edu.tum.ase.ase23.controller;

import edu.tum.ase.ase23.model.Delivery;
import edu.tum.ase.ase23.model.User;
import edu.tum.ase.ase23.service.DeliveryService;
import edu.tum.ase.ase23.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    UserService userService;
    @Autowired
    DeliveryService deliveryService;

    @GetMapping("/delivery/{userId}") //jwt and userId comparison
    public List<Delivery> getAllDeliveries(@PathVariable String userId) throws Exception {
        if (userId == null || userId.isEmpty()) { throw new Exception("User ID is required"); }
        return deliveryService.getDeliveriesOfUserFromUserId(userId);
    }

    @GetMapping("/{userId}") //jwt and userId comparison
    public User getAllInfo(@PathVariable String userId) throws Exception {
        if (userId == null || userId.isEmpty()) { throw new Exception("User ID is required"); }
        return userService.getInfoOfUserFromUserId(userId);
    }


    @PostMapping("/{userId}/password")
    public HttpStatus changeUserPassword(@PathVariable String userId, @RequestBody Map<String, String> payload) {

        try {
            String requestMadeBy = payload.get("requestMadeBy");
            User user = userService.getUserById(requestMadeBy);
            String oldPassword = "";
            Boolean isAdmin = false;
            if (!user.getUserType().equals("customer")) {
                oldPassword = payload.get("oldPassword");
                isAdmin = true;
            }
            String newPassword = payload.get("newPassword");
            userService.changePassword(userId, oldPassword, newPassword, isAdmin);
            return HttpStatus.ACCEPTED;
        }
        catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

}






