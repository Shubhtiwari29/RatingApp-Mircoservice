package com.ratingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RatingServiceApplication {

//	@Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }


	public static void main(String[] args) {
		SpringApplication.run(RatingServiceApplication.class, args);
	}

}
