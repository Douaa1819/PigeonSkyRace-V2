package com.youcode.pigeonskyracev2.security;

import com.youcode.pigeonskyracev2.service.Impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final Environment environment; // Pour accéder au profil actif

    public CustomAuthenticationProvider(@Lazy UserDetailsServiceImpl userDetailsService,
                                        PasswordEncoder passwordEncoder,
                                        Environment environment) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.environment = environment;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws BadCredentialsException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();


        UserDetails user = userDetailsService.loadUserByUsername(username);

        if (isTestProfileActive()) {
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    private boolean isTestProfileActive() {
        String[] activeProfiles = environment.getActiveProfiles();
        for (String profile : activeProfiles) {
            if ("test".equalsIgnoreCase(profile)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
