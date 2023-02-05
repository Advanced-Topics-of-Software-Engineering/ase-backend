package edu.tum.ase.ase23.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/dispatcher")
public class DispatcherController {

    @PostMapping("/edit_user/{userid}")
    public ResponseEntity<?> EditUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {

            return ResponseEntity.badRequest().contentType(MediaType.valueOf("application/json")).body(response.getOutputStream());
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf("application/json")).body(request.getAttribute("body"));
    }


    @GetMapping("/get_all_deliverers")
    public ResponseEntity<?> GetAllDeliverers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {
            return ResponseEntity.badRequest().contentType(MediaType.valueOf("application/json")).body(response.getOutputStream());
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf("application/json")).body(request.getAttribute("body"));
    }

    @GetMapping("/get_all_customers")
    public ResponseEntity<?> GetAllCustomers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {
            return ResponseEntity.badRequest().contentType(MediaType.valueOf("application/json")).body(response.getOutputStream());
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf("application/json")).body(request.getAttribute("body"));
    }

    @GetMapping("/get_all_dispatchers")
    public ResponseEntity<?> GetAllDispatchers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {
            return ResponseEntity.badRequest().contentType(MediaType.valueOf("application/json")).body(response.getOutputStream());
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf("application/json")).body(request.getAttribute("body"));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> GetUserByUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {
            return ResponseEntity.badRequest().contentType(MediaType.valueOf("application/json")).body(response.getOutputStream());
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf("application/json")).body(request.getAttribute("body"));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> GetUserByEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {
            return ResponseEntity.badRequest().contentType(MediaType.valueOf("application/json")).body(response.getOutputStream());
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf("application/json")).body(request.getAttribute("body"));
    }

    @GetMapping("/userid/{userid}")
    public ResponseEntity<?> GetUserByUserid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {
            return ResponseEntity.badRequest().contentType(MediaType.valueOf("application/json")).body(response.getOutputStream());
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf("application/json")).body(request.getAttribute("body"));
    }

    @GetMapping("/rfid/{rfidtoken}")
    public ResponseEntity<?> GetUserByRFIDToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {
            return ResponseEntity.badRequest().contentType(MediaType.valueOf("application/json")).body(response.getOutputStream());
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf("application/json")).body(request.getAttribute("body"));
    }
}

