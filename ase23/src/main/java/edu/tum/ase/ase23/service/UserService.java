package edu.tum.ase.ase23.service;

import edu.tum.ase.ase23.model.RoleEnum;
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
        return userRepository.findByRoles(RoleEnum.ROLE_DELIVERER);
    }

    public List<User> getAllCustomers() {
        return userRepository.findByRoles(RoleEnum.ROLE_CUSTOMER);
    }

    public List<User> getAllDispatchers() {
        return userRepository.findByRoles(RoleEnum.ROLE_DISPATCHER);
    }

    public User getUserById(String id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("User cannot find by id " + id));
    }

}
