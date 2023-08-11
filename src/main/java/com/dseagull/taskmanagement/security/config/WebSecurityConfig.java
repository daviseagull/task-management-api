package com.dseagull.taskmanagement.security.config;

import com.dseagull.taskmanagement.security.filter.JwtAuthenticationFilter;
import com.dseagull.taskmanagement.security.handler.UserAuthenticationEntrypoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private static final String[] PERMITTED_URIS = {
            "/v1/auth/authenticate",
            "/v1/auth/register",
            "/v3/api-docs/**",
            "/v3/api-docs/",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final UserAuthenticationEntrypoint entrypoint;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        return http
                .exceptionHandling(customizer -> customizer.authenticationEntryPoint(entrypoint))
                .csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, BasicAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(PERMITTED_URIS).permitAll()
                                .anyRequest().authenticated()
                )
                .build();
    }

}
