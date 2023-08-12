package com.dseagull.taskmanagement.domain.user.controller.swagger;

import com.dseagull.taskmanagement.domain.user.dto.UserInputDto;
import com.dseagull.taskmanagement.domain.user.dto.UserOutputDto;
import com.dseagull.taskmanagement.domain.user.dto.UsersOutputDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Email;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
public interface UserSwagger {

    @Operation(summary = "Paginated endpoint with sort and filter to get users")
    ResponseEntity<UsersOutputDto> getUsers(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "username") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir
    );

    @Operation(summary = "Gert user info")
    ResponseEntity<UserOutputDto> getUser(@PathVariable String id);

    @Operation(summary = "Disable an user")
    ResponseEntity<String> disableUser(@PathVariable String id);

    @Operation(summary = "Invite an user")
    ResponseEntity<String> inviteUser(@Email @PathVariable String email);

    @Operation(summary = "Edit user info")
    ResponseEntity<UserOutputDto> editUser(@PathVariable String id, @RequestBody UserInputDto user);
}
