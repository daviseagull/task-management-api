package com.dseagull.taskmanagement.user.dto;

import com.dseagull.taskmanagement.user.model.Name;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record ProfileInputDto(
        Name name,
        String password,
        String username
) implements Serializable {
}
