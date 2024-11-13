package com.userservice.UserServices.services.imple;

import com.userservice.UserServices.entities.Hotel;
import com.userservice.UserServices.entities.Rating;
import com.userservice.UserServices.entities.User;
import com.userservice.UserServices.exceptions.ResourceNotFoundException;
import com.userservice.UserServices.external.service.HotelService;
import com.userservice.UserServices.repositories.UserRepository;
import com.userservice.UserServices.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RestTemplate restTemplate;


    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {

        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with User ID : "+userId+"Not Found!!"));

        // FETCH RATINGS GIVEN BY THE ABOVE USER, FROM THE RATING SERVICE
        Rating[] ratingsOfUser =  restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{}",ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {

            //API CALL TO HOTEL SERVICE TO GET THE HOTELS
            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            //logger.info("Response Status Code {} ", forEntity.getStatusCode());
            //SET THE HOTEL TO THE RATING
            rating.setHotel(hotel);
            //RETURN THE RATING
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;

    }
}
