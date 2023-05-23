package pl.training.payments.adapters.events;

import org.mapstruct.Mapper;
import pl.training.payments.domain.PaymentUpdateEvent;

@Mapper(componentModel = "spring")
public interface EventsPaymentMapper {

    PaymentEventDto toDto(PaymentUpdateEvent paymentUpdateEvent);

}
