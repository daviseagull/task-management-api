package com.dseagull.taskmanagement.shared.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorOutputDto(LocalDateTime timestamp, String message, int status) {
}
