package com.dseagull.taskmanagement.domain.user.dto;

import com.dseagull.taskmanagement.domain.user.model.Role;
import lombok.Builder;

import java.util.Optional;

@Builder
public record UserFilters(String username, String email, Optional<Role> role) {
}
