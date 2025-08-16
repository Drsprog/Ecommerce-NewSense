package com.ecommerce.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;


@SpringBootApplication
public class BackendApplication {


	@Value("${spring.data.mongodb.uri}")
    private String mongoUri;

	public static void main(String[] args) {

		// Carga del .env antes de iniciar Spring
        Dotenv dotenv = Dotenv.configure()
                               .directory("./") // ruta relativa desde donde corres mvn
                               .ignoreIfMissing()
                               .load();

        // Poner las variables como propiedades de Spring
        System.setProperty("spring.data.mongodb.uri", dotenv.get("MONGO_URL"));
        System.setProperty("spring.data.mongodb.database", dotenv.get("MONGO_DB_NAME"));
		SpringApplication.run(BackendApplication.class, args);
	}

	  @PostConstruct
    public void printUri() {
        System.out.println("MongoDB URI = " + mongoUri);
    }

}
