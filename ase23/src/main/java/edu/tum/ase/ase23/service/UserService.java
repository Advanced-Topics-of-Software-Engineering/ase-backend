package edu.tum.ase.ase23.service;

import edu.tum.ase.ase23.model.User;
import edu.tum.ase.ase23.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllDeliverers() {
        return userRepository.findByUserType("deliverer");
    }

    public List<User> getAllCustomers() {
        return userRepository.findByUserType("customer");
    }

    public List<User> getAllDispatchers() {
        return userRepository.findByUserType("dispatcher");
    }

    public User getUserById(String id) throws Exception {
        return userRepository.findByUserId(id).orElseThrow(() -> new Exception("User cannot find by id " + id));
    }

}
