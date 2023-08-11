package com.dseagull.taskmanagement.shared.dto;

import lombok.Builder;

@Builder
public record ErrorOutputDto(String timestamp, String message, int status) {
}
