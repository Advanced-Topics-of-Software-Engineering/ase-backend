package edu.tum.ase.ase23.controller;

import edu.tum.ase.ase23.model.User;
import edu.tum.ase.ase23.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

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

}
