package com.dseagull.taskmanagement.domain.user.service;

import com.dseagull.taskmanagement.domain.user.User;
import com.dseagull.taskmanagement.domain.user.dto.UserFilters;
import com.dseagull.taskmanagement.domain.user.dto.UserInputDto;
import com.dseagull.taskmanagement.domain.user.dto.UserOutputDto;
import com.dseagull.taskmanagement.domain.user.dto.UsersOutputDto;
import com.dseagull.taskmanagement.domain.user.mapper.UserMapper;
import com.dseagull.taskmanagement.domain.user.model.Role;
import com.dseagull.taskmanagement.domain.user.model.Status;
import com.dseagull.taskmanagement.domain.user.repository.UserRepository;
import com.dseagull.taskmanagement.shared.exception.BusinessLogicException;
import com.dseagull.taskmanagement.shared.exception.ResourceNotFoundException;
import com.dseagull.taskmanagement.shared.service.EmailService;
import com.dseagull.taskmanagement.shared.util.ContextUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final EmailService emailService;
    private final UserRepository repository;
    private final UserMapper mapper;

    public UsersOutputDto getUsers(Pageable pageable, UserFilters filters) {
        Page<User> pages = repository.getUsers(pageable, filters);

        return UsersOutputDto.builder()
                .currentPage(pages.getNumber())
                .totalPages(pages.getTotalPages())
                .totalItems(pages.getTotalElements())
                .data((List<UserOutputDto>) mapper.toDtos(pages.getContent()))
                .build();
    }

    public void disableUser(String id) {
        User loggedUser = ContextUtil.getUser();

        isEqualToLoggedUser(id, loggedUser);
        User user = findUser(id);

        Status status = Status.builder()
                .enabled(Boolean.FALSE)
                .expired(user.getStatus().expired())
                .locked(user.getStatus().locked())
                .credentialsExpired(user.getStatus().credentialsExpired())
                .build();

        user.setStatus(status);

        repository.save(user);
    }

    public UserOutputDto getUser(String id) {
        return mapper.toDto(findUser(id));
    }

    public User findUser(String id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User with id not found: " + id, HttpStatus.NOT_FOUND);
        }
        return user.get();
    }

    private static void isEqualToLoggedUser(String userToDisableId, User loggedUser) {
        if (StringUtils.equals(loggedUser.getId(), userToDisableId)) {
            throw new BusinessLogicException("Logged user can't disable yourself", HttpStatus.CONFLICT);
        }
    }

    public void inviteUser(String email) {
        log.info("Inviting user to app: {}", email);

        String message =
                String.format(
                        "Hi %s, \nYou receive an invite to the task management app. Click here to finish you profile", email
                );

        emailService.sendEmail(email, "You receive an invite to task management app", message);
    }

    public UserOutputDto editUser(String id, UserInputDto userDto) {
        User loggedUser = ContextUtil.getUser();
        User user = findUser(id);

        isOperationValid(id, userDto, loggedUser, user);

        user = mapper.toEntity(user, userDto);

        repository.save(user);
        return mapper.toDto(user);
    }

    private static void isOperationValid(String id, UserInputDto userDto, User loggedUser, User user) {
        if (id.equalsIgnoreCase(loggedUser.getId()) && !userDto.role().equals(loggedUser.getRole())) {
            throw new BusinessLogicException("User doesn't have permission to change the role", HttpStatus.UNAUTHORIZED);
        }

        if (loggedUser.getRole().equals(Role.PROJECT_ADMIN) && user.getRole().equals(Role.APP_ADMIN)) {
            throw new BusinessLogicException("User doesn't have permission to change the role", HttpStatus.UNAUTHORIZED);
        }
    }
}
