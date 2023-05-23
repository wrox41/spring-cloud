package pl.training.payments.adapters.time.remote;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestTemplateTimeProvider {

    private final RestTemplate restTemplate;

    @Value("${timeApi.endpoint}/${timeApi.timezone}")
    @Setter
    private String timeApiUrl;

    public Optional<TimestampDto> getTime() {
        return Optional.ofNullable(restTemplate.getForObject(timeApiUrl, TimestampDto.class));
    }

}
