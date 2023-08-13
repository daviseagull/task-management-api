package com.dseagull.taskmanagement.security.usecase;

import com.dseagull.taskmanagement.security.util.JwtUtils;
import com.dseagull.taskmanagement.user.User;
import com.dseagull.taskmanagement.user.dto.AuthenticationOutputDto;
import com.dseagull.taskmanagement.user.dto.ProfileInputDto;
import com.dseagull.taskmanagement.user.exception.UserAlreadyExistsException;
import com.dseagull.taskmanagement.user.mapper.UserMapper;
import com.dseagull.taskmanagement.user.repository.UserRepository;
import com.dseagull.taskmanagement.user.util.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterUserUseCase {

    private final UserUtils utils;
    private final UserMapper mapper;
    private final UserRepository repository;
    private final JwtUtils jwtUtils;

    public AuthenticationOutputDto register(String email, ProfileInputDto profile) {
        checkIfIsValid(profile);
        User user = mapper.toEntity(utils.findUserByEmail(email), profile);
        user = repository.save(user);
        log.info("User with username created successfully: {}", profile.username());
        return generateResponse(user);
    }

    private void checkIfIsValid(ProfileInputDto profile) {
        if (repository.existsByUsername(profile.username())) {
            String message = "Account with username already exists: " + profile.username();
            throw new UserAlreadyExistsException(message, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    private AuthenticationOutputDto generateResponse(UserDetails user) {
        String token = jwtUtils.generateToken(user);
        return AuthenticationOutputDto.builder()
                .token(token)
                .build();
    }

}
