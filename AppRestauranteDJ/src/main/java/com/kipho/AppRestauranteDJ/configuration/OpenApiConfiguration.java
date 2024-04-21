package com.kipho.AppRestauranteDJ.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @io.swagger.v3.oas.annotations.info.Info(
        title = "AppRestauranteDJ",
        version = "v1.0",
        description = "Aplicação para controle de restaurante"
    )
)
public class OpenApiConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(OpenApiConfiguration.class);

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .components(new Components())
            .info(
                new Info()
                    .title("Aplicação para controle de restaurante") // Corrigido: método .title()
                    .version("v1.0") // Corrigido: método .version()
                    .license(
                        new License()
                            .name("Apache 2.0")
                            .url("http://springdoc.org")
                    )
            );
    }

    @Bean
    public void logSwaggerConfiguration() {
        logger.debug("Swagger configurado com sucesso. Documentação disponível em http://localhost:8080/swagger-ui/");
    }
}
    