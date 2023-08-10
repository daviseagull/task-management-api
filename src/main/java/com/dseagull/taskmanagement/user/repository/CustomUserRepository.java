package com.dseagull.taskmanagement.user.repository;

import com.dseagull.taskmanagement.user.User;
import com.dseagull.taskmanagement.user.dto.UserFilters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomUserRepository {

    Page<User> getUsers(Pageable pageable, UserFilters filters);
}
