package com.eteration_project.eteration_project.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//@Entity
//@Table(name = "Projects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ProjectName" , nullable = false)
    private  String projectName;

    @Column(name = "Description" , nullable = false)
    private  String  description;
}

