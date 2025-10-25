package com.mah.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        // Public routes
                        .pathMatchers("/actuator/**").permitAll()
                        // Role-based access
                        .pathMatchers("/api/hr/**").hasRole("HR")
                        .pathMatchers("/api/finance/**").hasRole("FINANCE")
                        .pathMatchers("/api/crm/**").hasAnyRole("ADMIN", "CRM")
                        // Anything else
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                );
        return http.build();
    }

    private ReactiveJwtAuthenticationConverterAdapter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        // Make Keycloak roles accessible under "realm_access.roles"
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            var roles = jwt.getClaimAsMap("realm_access");
            if (roles != null && roles.get("roles") instanceof java.util.List<?> roleList) {
                var mapper = new SimpleAuthorityMapper();
                mapper.setConvertToUpperCase(true);
                mapper.setPrefix("ROLE_");
                return mapper.mapAuthorities(
                        ((java.util.List<String>) roleList).stream()
                                .map(role -> "ROLE_" + role.toUpperCase())
                                .map(org.springframework.security.core.authority.SimpleGrantedAuthority::new)
                                .toList()
                );
            }
            return java.util.List.of();
        });

        return new ReactiveJwtAuthenticationConverterAdapter(converter);
    }
}
