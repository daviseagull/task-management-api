package com.dseagull.taskmanagement.user.mapper;

import com.dseagull.taskmanagement.user.User;
import com.dseagull.taskmanagement.user.dto.AuditUserDto;
import com.dseagull.taskmanagement.user.dto.ProfileInputDto;
import com.dseagull.taskmanagement.user.dto.UserInputDto;
import com.dseagull.taskmanagement.user.dto.UserOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;


@Mapper(componentModel = "spring")
public interface UserMapper {

    AuditUserDto toAuditUser(User user);

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

    @Mapping(target = "name", source = "profile.name")
    @Mapping(target = "password", source = "profile.password")
    @Mapping(target = "username", source = "profile.username")
    User toEntity(User user, ProfileInputDto profile);
}
