package edu.tum.ase.ase23.service;

import edu.tum.ase.ase23.model.RoleEnum;
import edu.tum.ase.ase23.model.User;
import edu.tum.ase.ase23.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllDeliverers() {
        List<User> users = userRepository.findAll();
        return users.stream().filter(user ->
                user.getRoles().stream().findAny().get().getRoleEnum().toString().equals("ROLE_DELIVERER")).toList();
    }

    public List<User> getAllCustomers() {
        List<User> users = userRepository.findAll();
        return users.stream().filter(user ->
                user.getRoles().stream().findAny().get().getRoleEnum().toString().equals("ROLE_CUSTOMER")).toList();
    }

    public List<User> getAllDispatchers() {
        List<User> users = userRepository.findAll();
        return users.stream().filter(user ->
                user.getRoles().stream().findAny().get().getRoleEnum().toString().equals("ROLE_DISPATCHER")).toList();
    }

    public User getUserById(String id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("User cannot find by id " + id));
    }

    public User getUserByRFIDToken(String RFIDToken) throws Exception {
        return userRepository.findByRFIDToken(RFIDToken).orElseThrow(() -> new Exception("User cannot find by RFID ID " + RFIDToken));
    }

}
