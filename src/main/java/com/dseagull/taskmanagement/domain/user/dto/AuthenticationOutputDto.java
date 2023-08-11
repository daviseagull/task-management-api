package com.dseagull.taskmanagement.domain.user.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record AuthenticationOutputDto(String token) implements Serializable {
}
