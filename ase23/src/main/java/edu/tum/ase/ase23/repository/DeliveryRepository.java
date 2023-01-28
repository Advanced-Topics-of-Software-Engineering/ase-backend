package edu.tum.ase.ase23.repository;

import edu.tum.ase.ase23.model.Delivery;
import edu.tum.ase.ase23.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends MongoRepository<Delivery, String> {
    public Optional<Delivery> findById(String id);
}
