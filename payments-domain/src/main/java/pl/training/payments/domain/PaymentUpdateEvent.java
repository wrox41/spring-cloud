package pl.training.payments.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentUpdateEvent {

    private String paymentId;
    private PaymentStatus paymentStatus;

}
