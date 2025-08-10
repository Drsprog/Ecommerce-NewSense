package com.ecommerce.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;


@SpringBootApplication
public class BackendApplication {


	@Value("${spring.data.mongodb.uri}")
    private String mongoUri;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	  @PostConstruct
    public void printUri() {
        System.out.println("MongoDB URI = " + mongoUri);
    }

}
