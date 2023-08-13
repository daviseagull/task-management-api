package com.dseagull.taskmanagement.user.usecase;

import com.dseagull.taskmanagement.shared.exception.BusinessLogicException;
import com.dseagull.taskmanagement.shared.util.ContextUtils;
import com.dseagull.taskmanagement.user.User;
import com.dseagull.taskmanagement.user.dto.UserInputDto;
import com.dseagull.taskmanagement.user.dto.UserOutputDto;
import com.dseagull.taskmanagement.user.mapper.UserMapper;
import com.dseagull.taskmanagement.user.model.Role;
import com.dseagull.taskmanagement.user.repository.UserRepository;
import com.dseagull.taskmanagement.user.util.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EditUserUseCase {

    private final UserUtils utils;
    private final UserMapper mapper;
    private final UserRepository repository;

    private static void isOperationValid(String id, UserInputDto userDto, User loggedUser, User user) {
        if (id.equalsIgnoreCase(loggedUser.getId()) && !userDto.role().equals(loggedUser.getRole())) {
            throw new BusinessLogicException("User doesn't have permission to change the role", HttpStatus.UNAUTHORIZED);
        }

        if (loggedUser.getRole().equals(Role.PROJECT_ADMIN) && user.getRole().equals(Role.APP_ADMIN)) {
            throw new BusinessLogicException("User doesn't have permission to change the role", HttpStatus.UNAUTHORIZED);
        }
    }

    public UserOutputDto editOne(String id, UserInputDto userDto) {
        log.info("Editing user with id: {} {}", id, userDto);
        User loggedUser = ContextUtils.getUser();
        User user = utils.findUserById(id);

        isOperationValid(id, userDto, loggedUser, user);

        user = mapper.toEntity(user, userDto);

        repository.save(user);
        return mapper.toDto(user);
    }
}
