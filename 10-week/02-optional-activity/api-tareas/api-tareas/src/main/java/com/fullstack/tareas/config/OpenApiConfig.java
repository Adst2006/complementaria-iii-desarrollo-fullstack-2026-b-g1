package com.fullstack.tareas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(new Info()
                .title("API de Tareas")
                .version("1.0.0")
                .description("API REST con CRUD en capas y persistencia (Desarrollo Fullstack - Semana 10)"));
    }
}
