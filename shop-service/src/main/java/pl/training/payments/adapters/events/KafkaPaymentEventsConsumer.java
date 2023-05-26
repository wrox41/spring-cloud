package pl.training.payments.adapters.events;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Log
//@Component("paymentEventsConsumer")
public class KafkaPaymentEventsConsumer implements Consumer<PaymentEventDto> {

    @Override
    public void accept(PaymentEventDto paymentEventDto) {
        log.info("New payment update: id: %s, new status: %s".formatted(paymentEventDto.getPaymentId(), paymentEventDto.getPaymentStatus()));
    }

}
