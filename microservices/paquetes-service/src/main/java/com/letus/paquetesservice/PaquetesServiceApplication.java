package com.letus.paquetesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.letus")
@SpringBootApplication
@EntityScan("com.letus.*")
public class PaquetesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaquetesServiceApplication.class, args);
	}

}
