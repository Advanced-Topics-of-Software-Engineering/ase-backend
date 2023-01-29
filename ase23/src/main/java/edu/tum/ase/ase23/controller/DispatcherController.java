package edu.tum.ase.ase23.controller;

import edu.tum.ase.ase23.model.User;
import edu.tum.ase.ase23.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/dispatcher")
public class DispatcherController {
    @Autowired
    UserService userService;

    @GetMapping("/deliverers")
    public List<User> getAllDeliverers(){
        return userService.getAllDeliverers();
    }

    @GetMapping("/customers")
    public List<User> getAllCustomers(){
        return userService.getAllCustomers();
    }

    @GetMapping("/dispatchers")
    public List<User> getAllDispatchers(){
        return userService.getAllDispatchers();
    }

    @PostMapping("/{userId}/password")
    public HttpStatus changeUserPassword(@PathVariable String userId, @RequestBody Map<String, String> payload) {

        try {
            String requestMadeBy = payload.get("requestMadeBy");
            User user = userService.getUserById(requestMadeBy);
            String oldPassword = "";
            Boolean isAdmin = false;
            if (!user.getUserType().equals("dispatcher")) {
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






