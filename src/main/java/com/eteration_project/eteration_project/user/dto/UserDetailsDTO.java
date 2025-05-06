package com.eteration_project.eteration_project.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDTO {

    private  String firstName;
    private  String lastName ;
    private  String email;
    private  String id;
    private  String username;


}
