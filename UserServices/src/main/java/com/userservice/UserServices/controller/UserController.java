package com.userservice.UserServices.controller;

import com.userservice.UserServices.entities.User;
import com.userservice.UserServices.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

//SINCE BELOW METHOD IS CALLING THE OTHER SERVICE[I.E DEPENDENT SERVICE]
// SO WE HAVE TO MAKE ONE FALLBACK METHOD FOR TO HANDLE/TOLERATE FAULTS[HAPPENS IN CASE OF SERVICE IS DOWN.]
int retryCount =1;
    @GetMapping("/{userId}")
//    @CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name="ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name="userRateLimiter", fallbackMethod="ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        logger.info("Fetching user with id: {}", userId);
        // logger.info("Retry Count: {}", retryCount);
        //retryCount++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    // BELOW IS FALL BACK METHOD FOR CIRCUIT BREAKER[RETRUN TYPE OF THE METHOD MUST BE SAME AS ABOVE METHOD]

    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
        logger.info("Fallback triggered: {}", ex.getMessage());
        // Return a user object with dummy ratings data
        User user = User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("Ratings service unavailable")
                .userId(userId)
                .build();
        return new ResponseEntity<>(user, HttpStatus.SERVICE_UNAVAILABLE);
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }


}
