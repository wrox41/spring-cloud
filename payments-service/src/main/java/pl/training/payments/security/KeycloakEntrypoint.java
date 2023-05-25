package pl.training.payments.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

public class KeycloakEntrypoint implements AuthenticationEntryPoint {

    private static final String API_PREFIX = "/api";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (request.getContextPath().contains(API_PREFIX)) {
            response.setStatus(SC_UNAUTHORIZED);
        } else {
            response.sendRedirect("http://localhost:8100");
        }
    }

}
