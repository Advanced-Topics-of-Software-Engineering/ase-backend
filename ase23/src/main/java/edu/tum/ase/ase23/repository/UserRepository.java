package edu.tum.ase.ase23.repository;

import edu.tum.ase.ase23.model.Box;
import edu.tum.ase.ase23.model.Role;
import edu.tum.ase.ase23.model.RoleEnum;
import edu.tum.ase.ase23.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByRoles(RoleEnum role);

    public Optional<User> findById(String type);

    public Optional<User> findByRFIDToken(String RFIDToken);

}
