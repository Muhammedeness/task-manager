package com.eteration_project.eteration_project.Mapper.MapStruct;
import com.eteration_project.eteration_project.dto.UserDto;
import com.eteration_project.eteration_project.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    //@Mapping(source = "firstName" , target = "firstName")
    //tüm field lar aynı olduğu için mapping ile source ve target eklemeye gerek kalmadı
    UserDto userToUserDto(User user);

}

