package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ImportResource(locations = {"classpath/applicationContext.xml"})
public class JavaStrategyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaStrategyApplication.class, args);
	}

}
