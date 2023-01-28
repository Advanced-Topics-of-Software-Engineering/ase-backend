package edu.tum.ase.ase23;

import com.mongodb.client.MongoClient;
import edu.tum.ase.ase23.model.Box;
import edu.tum.ase.ase23.repository.BoxRepository;
import edu.tum.ase.ase23.service.BoxService;
import edu.tum.ase.ase23.service.UserService;
import edu.tum.ase.ase23.repository.UserRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@SpringBootApplication
//@EnableMongoRepositories(basePackageClasses = {BoxRepository.class})
@EnableMongoRepositories(basePackageClasses = {UserRepository.class})
public class BoxApplication implements CommandLineRunner {
	@Autowired
	MongoClient mongoClient;

	@Autowired
	BoxService boxService;

	@Autowired
	UserService userService;

	private static final Logger log = LoggerFactory.getLogger(BoxApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BoxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("MongoClient = " + mongoClient.getClusterDescription());


//		String boxName = "Box#111q";
//		String boxStreetAddress = "Schrofelhofsatr 14";
//		Box box = boxService.createBox(new Box(boxName, boxStreetAddress));
//
//		log.info(String.format("Project %s is created with id %s",
//				box.getId(),
//				box.getName(),
//				box.getStreetAddress()
//		));
//		Box boxFind = boxService.findByName(boxName);
//		log.info(String.format("Found Project %s with id %s",
//				boxFind.getId(),
//				boxFind.getName(),
//				boxFind.getStreetAddress()
//		));


		List<Box> boxList = boxService.getAllBoxes();
		log.info("Number of Box n Database is " + boxList.size());
	}
}
