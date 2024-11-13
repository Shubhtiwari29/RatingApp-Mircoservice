package com.userservice.UserServices.repositories;


import com.userservice.UserServices.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String>
{

}
