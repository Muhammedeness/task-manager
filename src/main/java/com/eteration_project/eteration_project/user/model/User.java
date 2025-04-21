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

    @Column(name = "FirstName" , nullable = false)
    private String firstName;

    @Column(name = "Lastname" , nullable = false)
    private String lastName;

    @Column(name = "BirthDate" , nullable = false)
    private Date birthDate;

    @Column(name = "Email" , nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
}
