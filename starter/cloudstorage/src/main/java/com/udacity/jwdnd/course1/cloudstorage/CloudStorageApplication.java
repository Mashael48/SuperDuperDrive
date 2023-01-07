package com.udacity.jwdnd.course1.cloudstorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@SpringBootApplication
public class CloudStorageApplication {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(CloudStorageApplication.class, args);
	}

	@Bean
	CommandLineRunner run() throws Exception {
		return args -> {
			User dummyUser = new User(null, "dummy", "", "123", "First", "Last");
			userService.createUser(dummyUser);
		};
	}
}
