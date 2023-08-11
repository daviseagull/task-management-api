package com.dseagull.taskmanagement.domain.user.controller.swagger;


import com.dseagull.taskmanagement.domain.user.dto.AuthenticateInputDto;
import com.dseagull.taskmanagement.domain.user.dto.AuthenticationOutputDto;
import com.dseagull.taskmanagement.domain.user.dto.RegisterInputDto;
import com.dseagull.taskmanagement.shared.dto.ErrorOutputDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
public interface AuthenticationSwagger {

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User was created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthenticationOutputDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "User already exists or request has an invalid payload",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorOutputDto.class)
                            )
                    }
            )
    })
    ResponseEntity<AuthenticationOutputDto> register(@RequestBody RegisterInputDto request);


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
    ResponseEntity<AuthenticationOutputDto> authenticate(@RequestBody AuthenticateInputDto request);

}
