package br.com.food_city;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class FoodCityApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodCityApplication.class, args);
	}

}
