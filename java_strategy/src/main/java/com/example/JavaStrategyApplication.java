package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {"classpath/applicationContext.xml"})
@SpringBootApplication
public class JavaStrategyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaStrategyApplication.class, args);
	}

}
