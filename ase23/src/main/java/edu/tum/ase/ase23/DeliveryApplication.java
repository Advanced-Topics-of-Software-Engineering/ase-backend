package edu.tum.ase.ase23;

import com.mongodb.client.MongoClient;
import edu.tum.ase.ase23.model.Box;
import edu.tum.ase.ase23.model.Delivery;
import edu.tum.ase.ase23.model.User;
import edu.tum.ase.ase23.repository.DeliveryRepository;
import edu.tum.ase.ase23.repository.UserRepository;
import edu.tum.ase.ase23.service.BoxService;
import edu.tum.ase.ase23.service.DeliveryService;
import edu.tum.ase.ase23.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@SpringBootApplication
//@EnableMongoRepositories(basePackageClasses = {BoxRepository.class})
@EnableMongoRepositories(basePackageClasses = {DeliveryRepository.class})
public class DeliveryApplication implements CommandLineRunner {
    @Autowired
    MongoClient mongoClient;

    @Autowired
    BoxService boxService;

    @Autowired
    UserService userService;
    @Autowired
    DeliveryService deliveryService;


    private static final Logger log = LoggerFactory.getLogger(DeliveryApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DeliveryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("MongoClient = " + mongoClient.getClusterDescription());
        Box box = boxService.findByName("Box#1");
        User customer = userService.getAllCustomers().get(0);
        User deliverer = userService.getAllDeliverers().get(0);
        String status = "Ordered";
        String trackingID = "TrackingIDXD";
        Delivery delivery = deliveryService.createDelivery(
                new Delivery(box, customer, deliverer, status, trackingID)
        );
        User customer2 = userService.getAllCustomers().get(1);
        Delivery delivery2 = deliveryService.createDelivery(
                new Delivery(box, customer2, deliverer, status, trackingID)
        );
        log.info(String.format("Delivery %s is created with status: %s",
                				delivery.getId(),
                				delivery.getStatus()
                		));
        List<Delivery> deliveriesOfUser = deliveryService.getDeliveriesOfUserFromUserId(customer.getId());
        for(Delivery d: deliveriesOfUser) {
            log.info(String.format("getDeliveriesOfUserFromUserId is successful with id: %s, customerId: %s, status: %s",
                    d.getId(),
                    d.getCustomer().getId(),
                    d.getStatus()
            ));
        }
    }
}
