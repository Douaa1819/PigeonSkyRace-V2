package com.youcode.pigeonskyracev2.security;

import com.youcode.pigeonskyracev2.exception.CustomAccessDeniedHandler;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

        private final CustomAuthenticationProvider customAuthenticationProvider;
        public SecurityConfig(@Lazy CustomAuthenticationProvider customAuthenticationProvider ,AuthenticationConfiguration authenticationConfiguration) {
            this.customAuthenticationProvider = customAuthenticationProvider;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(authz -> authz
                            .requestMatchers("/api/v2/users/register", "/api/v2/users/login").permitAll()
                            .requestMatchers("/admin/**","/api/v2/users/{userId}/role").hasRole("ADMIN")
                            .requestMatchers("/api/v2/pigeons").hasRole("USER")
                            .requestMatchers("/api/v2/competitions").hasRole("ORGANIZER")
                            .anyRequest().authenticated()
                    )
                    .exceptionHandling(exception -> exception
                            .accessDeniedHandler(new CustomAccessDeniedHandler())
                            .authenticationEntryPoint((request, response, authException) -> {
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                response.getWriter().write("Invalid credentials. Please check your username and password.");
                            })
                    )
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .httpBasic(httpBasic -> {});
            return http.build();
        }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
