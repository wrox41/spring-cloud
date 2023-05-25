package pl.training.payments;

import feign.Capability;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.training.payments.adapters.time.SystemTimeProvider;
import pl.training.payments.domain.*;
import pl.training.payments.ports.EventEmitter;
import pl.training.payments.ports.PaymentRepository;
import pl.training.payments.ports.PaymentService;
import pl.training.payments.ports.TimeProvider;

@EnableFeignClients
@Configuration
public class PaymentsConfiguration {

    @Bean
    public PaymentIdGenerator paymentIdGenerator() {
        return new UuidPaymentIdGenerator();
    }

    @Bean
    public PaymentFeeCalculator percentagePaymentFeeCalculator() {
        return new PercentagePaymentFeeCalculator(0.01);
    }

    @Bean
    public PaymentService paymentService(PaymentIdGenerator paymentIdGenerator, PaymentFeeCalculator paymentFeeCalculator,
                                         PaymentRepository paymentRepository, TimeProvider timeProvider, EventEmitter eventEmitter) {
        return new PaymentProcessor(paymentIdGenerator, paymentFeeCalculator, paymentRepository, timeProvider, eventEmitter);
    }

    @Bean
    public TimeProvider systemTimeProvider() {
        return new SystemTimeProvider();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Capability capability(final MeterRegistry registry) {
        return new MicrometerCapability(registry);
    }

}
