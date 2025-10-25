package com.mah.hrservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/employees/**").hasRole("ADMIN")
                        .requestMatchers("/api/worktimes/**").hasAnyRole("EMPLOYEE","MANAGER")
                        .requestMatchers("/api/salaries/**").hasAnyRole("EMPLOYEE","MANAGER")
                        .requestMatchers("/api/reports/**").hasRole("MANAGER")
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(resourceServer -> resourceServer
                        .jwt(jwt -> {}) // JWT config handled via properties (issuer-uri / jwk-set-uri)
                );

        return http.build();
    }
}
