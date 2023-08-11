package com.dseagull.taskmanagement.domain.user.model;

import lombok.Builder;

@Builder
public record Status(
        boolean enabled,
        boolean expired,
        boolean locked,
        boolean credentialsExpired
) {
}
