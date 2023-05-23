package pl.training.payments.adapters.payments;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.training.payments.ports.PaymentsService;

import java.util.Optional;

@Component
@Log
@RequiredArgsConstructor
public class RestTemplatePaymentsAdapter implements PaymentsService {

    private final RestTemplate restTemplate;

    @Value("${api.payments}")
    @Setter
    private String paymentsApi;

    @Override
    public boolean pay(long amount, String currency) {
        var paymentRequestDto = new PaymentRequestDto();
        paymentRequestDto.setValue("%d %s".formatted(amount, currency));
        try {
            var paymentsDto = restTemplate.postForObject(paymentsApi, paymentRequestDto, PaymentDto.class);
            return Optional.ofNullable(paymentsDto)
                    .map(payment -> payment.status.equals("STARTED"))
                    .orElse(false);
        } catch (RestClientException restClientException) {
            log.info("Payment failed: " + restClientException.getMessage());
        }
        return false;
    }

}
