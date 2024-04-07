package com.stecyk.library.libraryprojectnetworktechstecyk.security;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    /**
     * Configuration of Swagger.
     */
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info().title("Library API - Lukasz Stecyk").version("0.5.3"))
                .addSecurityItem(new SecurityRequirement().addList("bearer Auth"))
                .components(new Components().addSecuritySchemes("bearer Auth", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }
}
