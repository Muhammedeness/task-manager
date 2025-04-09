package com.eteration_project.eteration_project.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

//@Entity
//@Table(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}
