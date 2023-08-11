package com.dseagull.taskmanagement.domain.user.mapper;

import com.dseagull.taskmanagement.domain.user.User;
import com.dseagull.taskmanagement.domain.user.dto.UserOutputDto;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserOutputDto toDto(User user);

    Collection<UserOutputDto> toDtos(Collection<User> users);

}
