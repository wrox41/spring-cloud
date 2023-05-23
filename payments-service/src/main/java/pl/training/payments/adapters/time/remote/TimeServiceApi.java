package pl.training.payments.adapters.time.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "timeProvider", url = "${timeApi.endpoint}")
public interface TimeServiceApi {

    @GetMapping("{timezone}")
    TimestampDto getTime(@PathVariable String timezone);

}
