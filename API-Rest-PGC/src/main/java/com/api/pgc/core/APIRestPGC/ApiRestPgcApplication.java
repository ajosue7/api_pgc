package com.api.pgc.core.APIRestPGC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.api.pgc.core.APIRestPGC.repository")
@SpringBootApplication
public class ApiRestPgcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestPgcApplication.class, args);
	}
}
