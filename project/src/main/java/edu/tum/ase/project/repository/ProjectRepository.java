package edu.tum.ase.project.repository;

import edu.tum.ase.project.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

// The MongoRepository is typed to the Document, and the type of the Document's ID
public interface ProjectRepository extends MongoRepository<Project, String> {
    public Optional<Project> findByName(String name);
}
