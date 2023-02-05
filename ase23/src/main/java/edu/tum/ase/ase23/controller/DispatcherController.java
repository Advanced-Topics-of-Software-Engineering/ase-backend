package edu.tum.ase.ase23.controller;

import edu.tum.ase.ase23.model.User;
import edu.tum.ase.ase23.repository.UserRepository;
import edu.tum.ase.ase23.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/dispatcher")
public class DispatcherController {

    @PostMapping("/edit_user/{userid}")
    public ResponseEntity<?> EditUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {
            return ResponseEntity.badRequest().contentType(MediaType.valueOf(response.getContentType())).body(response.getOutputStream());
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf(response.getContentType())).body(response.getOutputStream());
    }


    @GetMapping("/get_all_deliverers")
    public ResponseEntity<?> GetAllDeliverers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {
            return ResponseEntity.badRequest().contentType(MediaType.valueOf(response.getContentType())).body(response.getOutputStream());
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf(response.getContentType())).body(response.getOutputStream());
    }

    @GetMapping("/get_all_customers")
    public ResponseEntity<?> GetAllCustomers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {
            return ResponseEntity.badRequest().contentType(MediaType.valueOf(response.getContentType())).body(response.getOutputStream());
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf(response.getContentType())).body(response.getOutputStream());
    }

    @GetMapping("/get_all_dispatchers")
    public ResponseEntity<?> GetAllDispatchers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {
            return ResponseEntity.badRequest().contentType(MediaType.valueOf(response.getContentType())).body(response.getOutputStream());
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf(response.getContentType())).body(response.getOutputStream());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> GetUserByUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {
            return ResponseEntity.badRequest().contentType(MediaType.valueOf(response.getContentType())).body(response.getOutputStream());
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf(response.getContentType())).body(response.getOutputStream());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> GetUserByEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {
            return ResponseEntity.badRequest().contentType(MediaType.valueOf(response.getContentType())).body(response.getOutputStream());
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf(response.getContentType())).body(response.getOutputStream());
    }

    @GetMapping("/userid/{userid}")
    public ResponseEntity<?> GetUserByUserid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {
            return ResponseEntity.badRequest().contentType(MediaType.valueOf(response.getContentType())).body(response.getOutputStream());
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf(response.getContentType())).body(response.getOutputStream());
    }

    @GetMapping("/rfid/{rfidtoken}")
    public ResponseEntity<?> GetUserByRFIDToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() != 200) {
            return ResponseEntity.badRequest().body(response.getOutputStream());
        }
        return ResponseEntity.ok().body(response.getOutputStream());
    }
}

