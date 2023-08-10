package com.dseagull.taskmanagement.user.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record AuthenticateInputDto(String username, String password) implements Serializable {
}
