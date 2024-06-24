package com.szs.test.config;

import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public Info apiInfo() {
        return new Info()
                .title("API Documentation")
                .description("API Documentation using Springdoc OpenAPI");
    }

}
