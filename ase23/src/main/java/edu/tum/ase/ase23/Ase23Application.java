package edu.tum.ase.ase23;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"edu.tum.ase.ase23.repository"})
public class Ase23Application {

	@Autowired
	MongoClient mongoClient;
	public static void main(String[] args) {
		SpringApplication.run(Ase23Application.class, args);
	}

}
