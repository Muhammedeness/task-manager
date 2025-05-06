package com.eteration_project.eteration_project.project.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

//@Entity
//@Table(name = "Projects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {


    private UUID id;

    private  String projectName;

    private  String  description;

    private  String  state;
}

