package pl.training.payments.adapters.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.training.payments.domain.Payment;
import pl.training.payments.domain.PaymentRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ReactiveMongoPaymentRepositoryAdapter implements PaymentRepository {

    private final ReactiveMongoPaymentRepository paymentRepository;
    private final ReactiveMongoPaymentPersistenceMapper mapper;

    @Override
    public Mono<Payment> persist(Payment payment) {
        var paymentDocument = mapper.toDocument(payment);
        return paymentRepository.save(paymentDocument)
                .map(mapper::toDomain);

    }

    @Override
    public Flux<Payment> getAll() {
        return paymentRepository.findAll()
                .map(mapper::toDomain);
    }

}
