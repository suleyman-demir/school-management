package com.university.management.system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

public class SwaggerOpenAPI {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI();
    }
}
