package com.gdscewha.ewhaclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.gdscewha.ewhaclub.repository"})
@EntityScan(basePackages = {"com.gdscewha.ewhaclub"})
public class EwhaclubApplication {

	public static void main(String[] args) {
		SpringApplication.run(EwhaclubApplication.class, args);
	}

}
