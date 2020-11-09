package com.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;

@SpringBootApplication
@EnableEmailTools
public class NotificationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationApiApplication.class, args);
	}

}
