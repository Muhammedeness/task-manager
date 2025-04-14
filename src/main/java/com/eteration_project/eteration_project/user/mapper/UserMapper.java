package com.eteration_project.eteration_project.user.mapper;

import com.eteration_project.eteration_project.user.dto.AssignUserDto;
import com.eteration_project.eteration_project.user.dto.UserDeleteDto;
import com.eteration_project.eteration_project.user.dto.UserResponseDto;
import com.eteration_project.eteration_project.user.model.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    //@Mapping(source = "firstName" , target = "firstName")
    //tüm field lar aynı olduğu için mapping ile source ve target eklemeye gerek kalmadı
    UserResponseDto userToUserDto(User user);


    User  userDtoToUser(UserResponseDto userDto);

    AssignUserDto userDeleteDtoToassignUserDto(UserDeleteDto userDeleteDto);
}

