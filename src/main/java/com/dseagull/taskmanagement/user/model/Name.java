package com.dseagull.taskmanagement.user.model;

import lombok.Builder;

@Builder
public record Name(String first, String last) {
}
