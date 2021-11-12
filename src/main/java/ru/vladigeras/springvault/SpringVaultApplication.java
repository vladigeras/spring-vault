package ru.vladigeras.springvault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringVaultApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringVaultApplication.class, args);
	}
}