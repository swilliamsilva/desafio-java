package com.kipho.AppRestauranteDJ.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/Produto/**")
                        .uri("lb://Produto"))
                .route(p -> p.path("/Pedido/**")
                        .uri("lb://Pedido"))
                .route(p -> p.path("/ItemPedido/**")
                        .uri("lb://ItemPedido"))
                .build(); // Chave final
    }
}
