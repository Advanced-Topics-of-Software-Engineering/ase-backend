package edu.tum.ase.ase23;

import com.mongodb.client.MongoClient;
import edu.tum.ase.ase23.model.Box;
import edu.tum.ase.ase23.model.Delivery;
import edu.tum.ase.ase23.service.BoxService;
import edu.tum.ase.ase23.service.DeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import java.util.List;

public class DeliveryApplication {
    @Autowired
    MongoClient mongoClient;

    @Autowired
    DeliveryService  deliveryService;

    private static final Logger log = LoggerFactory.getLogger(BoxApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BoxApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("MongoClient = " + mongoClient.getClusterDescription());
        /*
        String deliveryName = "Box#1";
        String boxStreetAddress = "Schrofelhofstr 14";
        Box box = boxService.createBox(new Box(boxName, boxStreetAddress));

        log.info(String.format("Project %s is created with id %s",
                box.getId(),
                box.getName(),
                box.getStreetAddress()
        ));

        Box boxFind = boxService.findByName(boxName);

        log.info(String.format("Found Project %s with id %s",
                boxFind.getId(),
                boxFind.getName(),
                boxFind.getStreetAddress()
        ));

        List<Box> boxList = boxService.getAllBoxes();
        log.info("Number of Project in Database is " + boxList.size());
        */
    }
}
