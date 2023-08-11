package com.dseagull.taskmanagement.domain.user.dto;

import com.dseagull.taskmanagement.domain.user.model.Name;
import com.dseagull.taskmanagement.domain.user.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record RegisterInputDto(
        @NotNull Name name,
        @NotBlank String username,
        @Email String email,
        @NotBlank String password,
        @NotNull Role role
) implements Serializable {
}
