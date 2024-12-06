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
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

        private final CustomAuthenticationProvider customAuthenticationProvider;

        public SecurityConfig(@Lazy CustomAuthenticationProvider customAuthenticationProvider) {
            this.customAuthenticationProvider = customAuthenticationProvider;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(authz -> authz
                            .requestMatchers("/api/v1/users/register", "/api/v1/users/login").permitAll()
                            .requestMatchers("/admin/**","/api/v1/users/{userId}/role").hasRole("ADMIN")
                            .requestMatchers("/user/**").hasRole("USER")
                            .requestMatchers("/organizer/**").hasRole("ORGANIZER")
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
            return new AuthenticationManager() {
                @Override
                public Authentication authenticate(Authentication authentication) {
                    return customAuthenticationProvider.authenticate(authentication);
                }
            };
        }


        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }


        @Bean
        @Profile("test")
        public PasswordEncoder testPasswordEncoder() {
            return NoOpPasswordEncoder.getInstance();
        }

}
