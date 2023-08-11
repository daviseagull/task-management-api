package com.dseagull.taskmanagement.domain.user.mapper;

import com.dseagull.taskmanagement.domain.user.User;
import com.dseagull.taskmanagement.domain.user.dto.UserInputDto;
import com.dseagull.taskmanagement.domain.user.dto.UserOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserOutputDto toDto(User user);

    Collection<UserOutputDto> toDtos(Collection<User> users);

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "password", source = "user.password")
    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "name", source = "userDto.name")
    @Mapping(target = "email", source = "userDto.email")
    @Mapping(target = "role", source = "userDto.role")
    @Mapping(target = "status", source = "userDto.status")
    User toEntity(User user, UserInputDto userDto);

}
