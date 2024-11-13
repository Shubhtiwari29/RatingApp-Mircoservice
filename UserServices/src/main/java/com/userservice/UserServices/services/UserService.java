package com.userservice.UserServices.services;

import com.userservice.UserServices.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    //user operations

    //create
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get single user of given userId

    public User getUser(String userId);
}