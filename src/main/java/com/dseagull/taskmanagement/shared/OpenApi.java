package com.dseagull.taskmanagement.shared;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApi {

    public static final String SECURITY_TYPE = "bearer-jwt";

    private static Info getInfo() {
        return new Info()
                .title("Task Management API")
                .version("1.0.0-SNAPSHOT")
                .description("API responsible for a web app of task management");
    }

    @Bean
    public OpenAPI customizeOpenAPI() {
        return new OpenAPI()
                .info(getInfo())
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_TYPE))
                .components(getComponents());

    }

    private static Components getComponents() {
        return new Components()
                .addSecuritySchemes(
                        SECURITY_TYPE,
                        getSecurityScheme());
    }

    private static SecurityScheme getSecurityScheme() {
        return new SecurityScheme()
                .name(SECURITY_TYPE)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }
}
