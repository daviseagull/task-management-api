package com.dseagull.taskmanagement.domain.user.dto;

import com.dseagull.taskmanagement.domain.user.model.Name;
import com.dseagull.taskmanagement.domain.user.model.Status;
import com.dseagull.taskmanagement.domain.user.model.Role;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record UserOutputDto(
        String id,
        Name name,
        String username,
        String email,
        Role role,
        Status status
) implements Serializable {
}
