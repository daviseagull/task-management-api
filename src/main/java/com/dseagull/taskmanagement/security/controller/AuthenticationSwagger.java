package com.dseagull.taskmanagement.security.controller;


import com.dseagull.taskmanagement.shared.dto.ErrorOutputDto;
import com.dseagull.taskmanagement.user.dto.AuthenticateInputDto;
import com.dseagull.taskmanagement.user.dto.AuthenticationOutputDto;
import com.dseagull.taskmanagement.user.dto.ProfileInputDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
public interface AuthenticationSwagger {

    @Operation(summary = "Authenticate User")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User was authenticated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthenticationOutputDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "User not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorOutputDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Bad credentials",
                    content = @Content
            )
    })
    ResponseEntity<AuthenticationOutputDto> authenticate(@Valid @RequestBody AuthenticateInputDto request);

    @Operation(summary = "Finish profile after invite")
    ResponseEntity<AuthenticationOutputDto> finishProfile(
            @PathVariable String email,
            @RequestBody ProfileInputDto profile
    );
}
