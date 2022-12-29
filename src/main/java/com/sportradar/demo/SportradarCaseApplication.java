package com.sportradar.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SportradarCaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportradarCaseApplication.class, args);
	}

}
