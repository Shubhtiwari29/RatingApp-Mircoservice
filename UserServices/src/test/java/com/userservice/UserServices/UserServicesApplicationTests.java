package com.userservice.UserServices;

import com.userservice.UserServices.entities.Rating;
import com.userservice.UserServices.external.service.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServicesApplicationTests {

	@Test
	void contextLoads() {
	}

//		@Autowired
//		private RatingService ratingService;


//	    @Test
//        void testRatingService() {
//
//			Rating rating = Rating.builder().rating(9).userId("4b9dd733-e09f-449f-a828-05e315c8ec10").hotelId("1cab2b35-7698-42d1-b6f6-783b7eedd9bf").feedback("THis is created using feign client").build();
//        	 Rating savedRating =  ratingService.createRating(rating);
//
//			System.out.println("New Ratings Created");
//		}
}
