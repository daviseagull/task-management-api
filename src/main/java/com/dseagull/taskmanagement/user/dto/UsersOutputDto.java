package com.dseagull.taskmanagement.user.dto;

import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public record UsersOutputDto(
        List<UserOutputDto> data,
        int currentPage,
        long totalItems,
        long totalPages
) implements Serializable {
}
