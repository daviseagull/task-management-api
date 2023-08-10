package com.dseagull.taskmanagement.user.dto;

import com.dseagull.taskmanagement.user.model.Name;
import com.dseagull.taskmanagement.user.model.Role;
import com.dseagull.taskmanagement.user.model.Status;
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
