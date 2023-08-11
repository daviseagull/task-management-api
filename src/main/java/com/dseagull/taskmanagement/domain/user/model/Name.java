package com.dseagull.taskmanagement.domain.user.model;

import lombok.Builder;

@Builder
public record Name(String first, String last) {
}
