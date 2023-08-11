package com.dseagull.taskmanagement.domain.user.repository;

import com.dseagull.taskmanagement.domain.user.User;
import com.dseagull.taskmanagement.domain.user.dto.UserFilters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomUserRepository {

    Page<User> getUsers(Pageable pageable, UserFilters filters);
}
