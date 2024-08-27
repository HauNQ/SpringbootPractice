package com.example.CRUD_Student.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(@Value("${openapi.service.title}") String title, @Value("${openapi.service.api-docs}") String description,
                           @Value("${openapi.service.version}") String version, @Value("${openapi.service.server}") String serverUrl,
                           @Value("${openapi.service.server-name}") String serverNamme){
        return new OpenAPI().info(new Info().title(title).
                 description(description).version(version)
                .license(new License().name("License").url("domain/abc")))
                .servers(List.of(new Server().url(serverUrl).description(serverNamme)));
    }


    @Bean
    public GroupedOpenApi groupedOpenApi(){
        return GroupedOpenApi.builder()
                .group("API Student Service")
                .packagesToScan("com.example.CRUD_Student.rest")
                .build();
    }
}
