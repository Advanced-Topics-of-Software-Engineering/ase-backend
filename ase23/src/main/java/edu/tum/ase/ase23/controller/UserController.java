package edu.tum.ase.ase23.controller;
import edu.tum.ase.ase23.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/deliverers")
    public ResponseEntity<?> getAllDeliverers(){
        return ResponseEntity.ok(userService.getAllDeliverers());
    }
    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers(){
        return ResponseEntity.ok(userService.getAllCustomers());
    }

    @GetMapping("/dispatchers")
    public ResponseEntity<?> getAllDispatchers(){
        return ResponseEntity.ok(userService.getAllDispatchers());
    }

}
