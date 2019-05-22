package com.api.pgc.core.APIRestPGC;

import com.api.pgc.core.APIRestPGC.config.fileUpload.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.boot.builder.SpringApplicationBuilder;

@EnableConfigurationProperties({
		FileStorageProperties.class
})
@EnableJpaRepositories(basePackages = "com.api.pgc.core.APIRestPGC.repository")
@SpringBootApplication
public class ApiRestPgcApplication extends SpringBootServletInitializer {

	 /**
	 * Used when run as JAR
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApiRestPgcApplication.class, args);
	}

	/**
	 * Used when run as WAR
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ApiRestPgcApplication.class);
	}
}
