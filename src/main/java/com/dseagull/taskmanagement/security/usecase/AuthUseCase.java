package com.dseagull.taskmanagement.security.usecase;

import com.dseagull.taskmanagement.security.util.JwtUtils;
import com.dseagull.taskmanagement.user.dto.AuthenticateInputDto;
import com.dseagull.taskmanagement.user.dto.AuthenticationOutputDto;
import com.dseagull.taskmanagement.user.util.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthUseCase {

    private final UserUtils userUtils;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    private AuthenticationOutputDto generateResponse(UserDetails user) {
        String token = jwtUtils.generateToken(user);
        return AuthenticationOutputDto.builder()
                .token(token)
                .build();
    }

    public AuthenticationOutputDto authenticate(AuthenticateInputDto request) {
        UserDetails userDetails = userUtils.findUserByUsername(request.username());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        );

        authenticationManager.authenticate(token);

        log.info("User authenticated: {}", userDetails.getUsername());
        return generateResponse(userDetails);
    }
}
