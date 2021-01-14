package io.abhilash.spring.securityjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.abhilash.spring.securityjwt.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class Application {
	/*
	* project created from https://youtu.be/X80nJ5T7YpE
	*
	*/
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
