package com.savek.RestJpaSpringExampleApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestJpaSpringExampleAppApplication {

	private static final Logger log = LoggerFactory.getLogger(RestJpaSpringExampleAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestJpaSpringExampleAppApplication.class, args);
	}
}
