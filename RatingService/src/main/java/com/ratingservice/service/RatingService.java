package com.ratingservice.service;

import com.ratingservice.entities.Rating;
import com.ratingservice.repository.RatingRepository;

import java.util.List;

public interface RatingService {

    Rating create(Rating rating);

    List<Rating> getRatings();

    List<Rating> getRatingsByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotelId);
}
