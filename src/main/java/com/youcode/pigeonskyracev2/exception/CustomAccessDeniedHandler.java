package com.youcode.pigeonskyracev2.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        String jsonResponse = "{" +
                "\"status\": 403," +
                "\"message\": \"Access denied: You do not have permission to access this resource\"," +
                "\"path\": \"" + request.getRequestURI() + "\"," +
                "\"timestamp\": \"" + LocalDateTime.now() + "\"" +
                "}";

        response.getWriter().write(jsonResponse);
    }
}
