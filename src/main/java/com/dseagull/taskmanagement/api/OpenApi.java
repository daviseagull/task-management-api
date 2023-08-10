package com.dseagull.taskmanagement.api;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApi {

    private static Info getInfo() {
        return new Info()
                .title("Task Management API")
                .version("1.0.0-SNAPSHOT")
                .description("API responsible for a web app of task management");
    }

    @Bean
    public OpenAPI customizeOpenAPI() {
        return new OpenAPI()
                .info(getInfo());
    }
}
