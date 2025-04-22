package com.eteration_project.eteration_project.user.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

//@Entity
//@Table(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private String firstName;

    private String lastName;

    private Date birthDate;

    private String email;

    private String password;
}
