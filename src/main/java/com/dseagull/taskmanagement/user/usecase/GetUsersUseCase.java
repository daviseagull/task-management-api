package com.dseagull.taskmanagement.user.usecase;

import com.dseagull.taskmanagement.user.User;
import com.dseagull.taskmanagement.user.dto.UserFilters;
import com.dseagull.taskmanagement.user.dto.UserOutputDto;
import com.dseagull.taskmanagement.user.dto.UsersOutputDto;
import com.dseagull.taskmanagement.user.mapper.UserMapper;
import com.dseagull.taskmanagement.user.repository.UserRepository;
import com.dseagull.taskmanagement.user.util.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetUsersUseCase {

    private final UserUtils utils;
    private final UserMapper mapper;
    private final UserRepository repository;

    public UserOutputDto getOne(String id) {
        return mapper.toDto(utils.findUserById(id));
    }

    public UsersOutputDto getMany(Pageable pageable, UserFilters filters) {
        log.info("Getting users: {} {}", pageable.toString(), filters.toString());
        Page<User> pages = repository.getUsers(pageable, filters);

        return UsersOutputDto.builder()
                .currentPage(pages.getNumber())
                .totalPages(pages.getTotalPages())
                .totalItems(pages.getTotalElements())
                .data((List<UserOutputDto>) mapper.toDtos(pages.getContent()))
                .build();
    }
}
