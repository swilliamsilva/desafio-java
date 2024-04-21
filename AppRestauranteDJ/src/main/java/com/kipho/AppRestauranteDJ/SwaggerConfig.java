package com.kipho.AppRestauranteDJ;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections; // Corrigido: importação correta

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

    @Bean
    public Docket api() {
        logger.debug("Swagger configurado com sucesso. Documentação disponível em http://localhost:8080/swagger-ui/");
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kipho.AppRestauranteDJ"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
            "AppRestauranteDJ",
            "Aplicação para controle de restaurante",
            "Versão 1.0",
            "Termos de serviço",
            new Contact("William Silva", "kipho.com", "khipo@example.com"),
            "Licença",
            "URL da licença",
            Collections.emptyList() // Usando a classe Collections correta
        );
    }
}
