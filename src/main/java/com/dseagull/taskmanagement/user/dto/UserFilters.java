package com.dseagull.taskmanagement.user.dto;

import com.dseagull.taskmanagement.user.model.Role;
import lombok.Builder;

import java.util.Optional;

@Builder
public record UserFilters(String username, String email, Optional<Role> role) {
}
