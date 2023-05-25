package pl.training.payments.security;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static pl.training.payments.security.Token.getAuthHeaderValue;

public class RestTemplateTokenInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        getAuthHeaderValue().ifPresent(headerValue -> request.getHeaders().set(AUTHORIZATION, headerValue));
        return execution.execute(request, body);
    }

}
