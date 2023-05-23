package pl.training.payments.adapters.time.remote;

import org.mapstruct.Mapper;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface RemoteTimeRestMapper {

    default Instant toPort(TimestampDto timestampDto) {
        return Instant.ofEpochSecond(timestampDto.getUnixtime());
    }

}
