package com.dseagull.taskmanagement.shared.config;

import com.dseagull.taskmanagement.user.User;
import com.dseagull.taskmanagement.user.dto.AuditUserDto;
import com.dseagull.taskmanagement.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableMongoAuditing
@RequiredArgsConstructor
public class AuditingConfig {

    private final UserMapper mapper;

    @Bean
    public AuditorAware<AuditUserDto> myAuditorProvider() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(User.class::cast)
                .map(mapper::toAuditUser);
    }

}
