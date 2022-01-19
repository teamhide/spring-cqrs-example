package com.example.springcqrsexample;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringCqrsExampleApplication {
	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application-local.yml,"
			+ "classpath:aws.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(((SpringCqrsExampleApplication.class)))
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}
}
