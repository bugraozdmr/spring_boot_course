package com.grant.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
manuel defined packages for scan // COMPONENT SCANNING
@SpringBootApplication(
		scanBasePackages = {"com.grant.springcoredemo",
							"com.grant.util"}
)
*/

@SpringBootApplication
public class SpringcoredemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcoredemoApplication.class, args);
	}

}
