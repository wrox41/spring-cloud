package pl.training.payments.adapters.events;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import pl.training.payments.domain.PaymentUpdateEvent;
import pl.training.payments.ports.EventEmitter;

@Component
@RequiredArgsConstructor
public class KafkaEventEmitter implements EventEmitter {

    private static final String PAYMENTS_BINDING_NAME = "payments-out-0";
    private final StreamBridge streamBridge;
    private final EventsPaymentMapper mapper;

    @Override
    public void emit(PaymentUpdateEvent event) {
        var paymentEventDto = mapper.toDto(event);
        paymentEventDto.setType("UPDATE");
        streamBridge.send(PAYMENTS_BINDING_NAME, paymentEventDto);
    }

}
