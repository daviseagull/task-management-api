package com.dseagull.taskmanagement.user.dto;

import com.dseagull.taskmanagement.user.model.Role;
import lombok.Builder;

@Builder
public record AuditUserDto(String id, String email, Role role) {
}
