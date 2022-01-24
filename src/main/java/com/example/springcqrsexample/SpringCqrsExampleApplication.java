package com.example.springcqrsexample;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringCqrsExampleApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(((SpringCqrsExampleApplication.class)))
				.profiles("local")
				.run(args);
	}
}
