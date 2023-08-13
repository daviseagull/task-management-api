package com.dseagull.taskmanagement.user.dto;

import com.dseagull.taskmanagement.user.model.Name;
import com.dseagull.taskmanagement.user.model.Role;
import com.dseagull.taskmanagement.user.model.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record UserInputDto(
        @NotNull Name name,
        @Email String email,
        @NotBlank Role role,
        @NotNull Status status
) implements Serializable {

}
