package edu.tum.ase.ase23.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
@RestController
@RequestMapping("/user")
public class UserController {


    @PostMapping("/change_password/{username}")
    public ResponseEntity<?> ChangePassword(@PathVariable String username, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {
            return ResponseEntity.badRequest().contentType(MediaType.valueOf("application/json")).body(response.getOutputStream());
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf("application/json")).body(request.getAttribute("body"));
    }

    @PostMapping("/change_email/{username}")
    public ResponseEntity<?> ChangeEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {
            return ResponseEntity.badRequest().contentType(MediaType.valueOf("application/json")).body(response.getOutputStream());
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf("application/json")).body(request.getAttribute("body"));
    }

    @GetMapping("{username}")
    public ResponseEntity<?> GetUserByUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {
            return ResponseEntity.badRequest().contentType(MediaType.valueOf("application/json")).body(response.getOutputStream());
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf("application/json")).body(request.getAttribute("body"));
    }

}

