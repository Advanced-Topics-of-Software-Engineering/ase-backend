package edu.tum.ase.ase23;

import com.mongodb.client.MongoClient;
import edu.tum.ase.ase23.config.CorsConfig;
import edu.tum.ase.ase23.model.Box;
import edu.tum.ase.ase23.model.Delivery;
import edu.tum.ase.ase23.model.User;
import edu.tum.ase.ase23.service.BoxService;
import edu.tum.ase.ase23.service.DeliveryService;
import edu.tum.ase.ase23.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@Import(CorsConfig.class)
@EnableMongoRepositories(basePackages = {"edu.tum.ase.ase23.repository"})
public class BoxApplication implements CommandLineRunner {

	@Autowired
	MongoClient mongoClient;

    @Autowired
    UserService userService;

    @Autowired
    DeliveryService deliveryService;

    @Autowired
    BoxService boxService;

	private static final Logger log = LoggerFactory.getLogger(BoxApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BoxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("MongoClient = " + mongoClient.getClusterDescription());
        Box box = boxService.findByName("Box#1");
        String customerID = userService.getAllCustomers().get(0).getId();
        String delivererID = userService.getAllDeliverers().get(0).getId();
        String status = "Ordered";
        String trackingID = "TrackingIDXD";
        Delivery delivery = deliveryService.createDelivery(
                new Delivery(box, customerID, delivererID, status, trackingID)
        );
        String customer2ID = userService.getAllCustomers().get(1).getId();
        Delivery delivery2 = deliveryService.createDelivery(
                new Delivery(box, customer2ID, delivererID, status, trackingID)
        );
	}
}