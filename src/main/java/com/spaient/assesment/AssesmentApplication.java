package com.spaient.assesment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AssesmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssesmentApplication.class, args);
	}

}
