package pl.training.payments.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Optional;

public class Token {

    public static final String PREFIX = "bearer ";

    public static Optional<String> getValue() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken jwtAuthentication) {
            return Optional.of(jwtAuthentication)
                    .map(JwtAuthenticationToken::getToken)
                    .map(Jwt::getTokenValue);
        } else {
            return Optional.empty();
        }
    }

    public static Optional<String> getAuthHeaderValue() {
        return getValue().map(value -> PREFIX + value);
    }

}
