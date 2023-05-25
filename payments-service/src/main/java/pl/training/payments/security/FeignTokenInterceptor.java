package pl.training.payments.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static pl.training.payments.security.Token.getAuthHeaderValue;

@Component
public class FeignTokenInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        getAuthHeaderValue().ifPresent(headerValue -> requestTemplate.header(AUTHORIZATION, headerValue));
        ;
    }

}
