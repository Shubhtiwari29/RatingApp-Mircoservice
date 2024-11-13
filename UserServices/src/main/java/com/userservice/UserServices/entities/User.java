package com.userservice.UserServices.entities;

import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {

    @Id
    @Column(name = "user_Id")
    private String userId;

    @Column(name = "user_name", length = 50)
    private String name;

    @Column(name = "user_email")

    private String email;
    @Column(name = "user_about")
    private String about;


    @Transient
    private List<Rating> ratings=new ArrayList<>();


}
