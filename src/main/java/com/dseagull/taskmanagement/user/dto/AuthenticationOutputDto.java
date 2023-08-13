package com.dseagull.taskmanagement.user.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record AuthenticationOutputDto(String token) implements Serializable {
}
