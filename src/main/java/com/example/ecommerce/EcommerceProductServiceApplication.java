package com.example.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.ecommerce.repository")
public class EcommerceProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceProductServiceApplication.class, args);
	}

}
