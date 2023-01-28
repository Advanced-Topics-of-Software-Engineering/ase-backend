package edu.tum.ase.ase23.repository;

import edu.tum.ase.ase23.model.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends MongoRepository<Delivery, String> {
    public List<Delivery> findByCustomer(String name);
    public Optional<Delivery> findByUserId(String userId);
}
