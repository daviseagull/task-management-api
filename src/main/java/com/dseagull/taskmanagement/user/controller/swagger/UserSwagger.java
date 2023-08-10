package com.dseagull.taskmanagement.user.controller.swagger;

import com.dseagull.taskmanagement.user.dto.UsersOutputDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
public interface UserSwagger {

    @Operation(summary = "Paginated endpoint with sort and filter  to get users")
    @ApiResponse(
            responseCode = "200",
            description = "Users list",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = UsersOutputDto.class)
                    )
            )
    )
    ResponseEntity<UsersOutputDto> getUsers(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "username") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir
    );

    @Operation(summary = "Disable an user")
    ResponseEntity<String> disableUser(@PathVariable String id);
}
