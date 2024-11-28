package spss.project.backend.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity
public class SPSSSecurityConfig {
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests((requests) -> requests
                                                .requestMatchers(HttpMethod.GET, "/document", "/report")
                                                .permitAll()
                                                .anyRequest()
                                                .access(new WebExpressionAuthorizationManager(
                                                                "hasIpAddress('127.0.0.1')")))
                                .formLogin((form) -> form
                                                .disable())
                                .httpBasic(basic -> basic
                                                .disable())
                                .passwordManagement(password -> password
                                                .disable())
                                .logout((logout) -> logout
                                                .permitAll())
                                .csrf((csrf) -> csrf
                                                .disable());

                return http.build();
        }
}
