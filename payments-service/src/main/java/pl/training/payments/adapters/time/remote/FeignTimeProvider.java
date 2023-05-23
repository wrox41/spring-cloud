package pl.training.payments.adapters.time.remote;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeignTimeProvider {

    private final TimeServiceApi timeServiceApi;

    @Value("${timeApi.timezone}")
    @Setter
    private String timezone;

    public Optional<TimestampDto> getTime() {
        return Optional.ofNullable(timeServiceApi.getTime(timezone));
    }

}
