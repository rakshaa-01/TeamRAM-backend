package com.team.ram.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BackendApplication {
	public static void main(String[] args) {
		//SpringApplication.run(BackendApplication.class, args); //Statement giving error in test main
		new SpringApplicationBuilder(BackendApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
	}
}
