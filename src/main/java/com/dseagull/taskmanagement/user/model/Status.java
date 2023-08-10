package com.dseagull.taskmanagement.user.model;

import lombok.Builder;

@Builder
public record Status(
        boolean enabled,
        boolean expired,
        boolean locked,
        boolean credentialsExpired
) {
}
