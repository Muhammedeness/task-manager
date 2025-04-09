package com.eteration_project.eteration_project.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(name = "Firstname" , nullable = false)
    private String firstName;

    @Column(name = "Lastname" , nullable = false)
    private String lastName;

    @Column(name = "BirthDate" , nullable = false)
    private Date birthDate;

    @Column(name = "EmailAddress" , nullable = false)
    private String email;

    //one user can attend many projects
    //and one project can have many users
    //so it is a manttomany relation

    @ManyToMany(mappedBy = "assignedUsers")
    private List<Project> projects;

}
