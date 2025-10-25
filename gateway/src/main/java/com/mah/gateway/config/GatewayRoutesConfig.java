package com.mah.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Example: CRM Service
                .route("crm-service", r -> r.path("/api/crm/**")
                        .filters(f -> f.rewritePath("/api/crm/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8081"))

                // Example: HR Service
                .route("hr-service", r -> r.path("/api/hr/**")
                        .filters(f -> f.rewritePath("/api/hr/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8082"))

                // Example: Finance Service
                .route("finance-service", r -> r.path("/api/finance/**")
                        .filters(f -> f.rewritePath("/api/finance/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8083"))

                .build();
    }
}

