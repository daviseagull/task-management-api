package com.dseagull.taskmanagement.user.service;

import com.dseagull.taskmanagement.shared.exception.BusinessLogicException;
import com.dseagull.taskmanagement.shared.exception.ResourceNotFoundException;
import com.dseagull.taskmanagement.user.User;
import com.dseagull.taskmanagement.user.dto.UserFilters;
import com.dseagull.taskmanagement.user.dto.UserOutputDto;
import com.dseagull.taskmanagement.user.dto.UsersOutputDto;
import com.dseagull.taskmanagement.user.mapper.UserMapper;
import com.dseagull.taskmanagement.user.model.Status;
import com.dseagull.taskmanagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

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
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (StringUtils.equals(loggedUser.getId(), id)) {
            throw new BusinessLogicException("Logged user can't disable yourself");
        }

        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User with id not found: " + id);
        }

        Status status = Status.builder()
                .enabled(Boolean.FALSE)
                .expired(user.get().getStatus().expired())
                .locked(user.get().getStatus().locked())
                .credentialsExpired(user.get().getStatus().credentialsExpired())
                .build();

        user.get().setStatus(status);

        repository.save(user.get());
    }
}
