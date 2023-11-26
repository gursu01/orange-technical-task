package gursu.orange.technicaltask.mappers;

import gursu.orange.technicaltask.dto.CurrencyExchangeRequestDto;
import gursu.orange.technicaltask.enities.CurrencyExchange;

import java.math.RoundingMode;
import java.time.Instant;

public class CurrencyExchangeMapper {

    public static CurrencyExchange toEntity(CurrencyExchangeRequestDto requestDto) {
        return CurrencyExchange.builder()
                .sourceCurrencyCode(requestDto.getSourceCurrencyCode())
                .targetCurrencyCode(requestDto.getTargetCurrencyCode())
                .receivedAmount(requestDto.getReceivedAmount().setScale(2, RoundingMode.HALF_EVEN))
                .releasedAmount(requestDto.getReleasedAmount().setScale(2, RoundingMode.HALF_EVEN))
                .genDate(Instant.now())
                .modDate(Instant.now())
                .build();

    }
}
