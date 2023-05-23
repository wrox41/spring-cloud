package pl.training.payments.adapters.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import pl.training.payments.ports.PaymentsService;

import java.util.Optional;

@Log
@Component
@RequiredArgsConstructor
public class FeignPaymentsAdapter implements PaymentsService {

    private final PaymentsApi paymentsApi;

    @Override
    public boolean pay(long amount, String currency) {
        var paymentRequestDto = new PaymentRequestDto();
        paymentRequestDto.setValue("%d %s".formatted(amount, currency));
        try {
            var paymentsDto = paymentsApi.pay(paymentRequestDto);
            return Optional.ofNullable(paymentsDto)
                    .map(payment -> payment.status.equals("STARTED"))
                    .orElse(false);
        } catch (RestClientException restClientException) {
            log.info("Payment failed: " + restClientException.getMessage());
        }
        return false;
    }

}
