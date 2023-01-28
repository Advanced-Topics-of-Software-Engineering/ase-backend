package edu.tum.ase.ase23.service;

import edu.tum.ase.ase23.model.User;
import edu.tum.ase.ase23.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return userRepository.findById(id).orElseThrow(() -> new Exception("User cannot find by id " + id));
    }

    public void changePassword(String id, String oldPassword, String newPassword, Boolean isAdmin) throws Exception {
        if (isAdmin == null) {
            isAdmin = false;
        }
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User cannot find by id " + id));
        if (!isAdmin) {
            if (user.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword);
            }
            else{
                throw new Exception("Passwords do not match!");
            }
        }
        else {
            user.setPassword(newPassword);
        }
        userRepository.save(user);
    }

}
