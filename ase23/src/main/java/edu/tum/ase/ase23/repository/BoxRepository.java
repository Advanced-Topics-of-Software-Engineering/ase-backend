package edu.tum.ase.ase23.repository;

import edu.tum.ase.ase23.model.Box;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

// The MongoRepository is typed to the Document, and the type of the Document's ID
public interface BoxRepository extends MongoRepository<Box, String> {
    public Optional<Box> findByName(String name);
}
