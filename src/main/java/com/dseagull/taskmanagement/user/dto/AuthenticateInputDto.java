package com.dseagull.taskmanagement.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record AuthenticateInputDto(
        @NotBlank String username,
        @NotBlank String password
) implements Serializable {
}
