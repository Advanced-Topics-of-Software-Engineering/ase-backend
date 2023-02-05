package edu.tum.ase.ase23.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.management.relation.RoleNotFoundException;
import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {
            return ResponseEntity.badRequest().contentType(MediaType.valueOf(response.getContentType())).body(response.getOutputStream());
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf(response.getContentType())).body(response.getOutputStream());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {
            return ResponseEntity.badRequest().contentType(MediaType.valueOf(response.getContentType())).body(response.getOutputStream());
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf(response.getContentType())).body(response.getOutputStream());
    }
}
