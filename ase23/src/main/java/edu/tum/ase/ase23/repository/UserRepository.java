package edu.tum.ase.ase23.repository;

import edu.tum.ase.ase23.model.Box;
import edu.tum.ase.ase23.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    public List<User> findByUserType(String type);

    public Optional<User> findById(String type);

    public Optional<User> findByEmail(String email);
}
