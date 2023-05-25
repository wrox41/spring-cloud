package pl.training.payments;

import feign.Capability;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.training.payments.domain.ConstantDiscountCalculator;
import pl.training.payments.domain.DiscountCalculator;
import pl.training.payments.domain.OrderProcessor;
import pl.training.payments.ports.PaymentsService;
import pl.training.payments.ports.ShopService;

@Log
@EnableFeignClients
@Configuration
public class ShopConfiguration {

    @Bean
    public ShopService shopService(PaymentsService paymentsService,DiscountCalculator discountCalculator) {
        return new OrderProcessor(paymentsService, discountCalculator);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RefreshScope
    @Bean
    public DiscountCalculator discountCalculator(@Value("${discount}") long discount) {
        log.info("Creating discount calculator");
        return new ConstantDiscountCalculator(discount);
    }

    @Bean
    public Capability capability(final MeterRegistry registry) {
        return new MicrometerCapability(registry);
    }

}
