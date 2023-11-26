package gursu.orange.technicaltask.mappers;

import gursu.orange.technicaltask.dto.ExchangeRateRequestDto;
import gursu.orange.technicaltask.dto.ExchangeRateResponseDto;
import gursu.orange.technicaltask.enities.ExchangeRate;

import java.math.RoundingMode;
import java.time.Instant;
import java.time.ZoneId;

public class ExchangeRateMapper {
    public static ExchangeRate toEntity(ExchangeRateRequestDto dto) {
        return ExchangeRate.builder()
                .rate(dto.getRate())
                .conversionRate(dto.getConversionRate().setScale(2, RoundingMode.HALF_EVEN))
                .currencyCode(dto.getCurrencyCode())
                .genDate(Instant.now())
                .modDate(Instant.now())
                .build();
    }

    public static ExchangeRateResponseDto toResponseDto(ExchangeRate exchangeRate) {
        return ExchangeRateResponseDto.builder()
                .rate(exchangeRate.getRate())
                .conversionRate(exchangeRate.getConversionRate().setScale(2, RoundingMode.HALF_EVEN))
                .currencyCode(exchangeRate.getCurrencyCode())
                .localDate(exchangeRate.getGenDate().atZone(ZoneId.systemDefault()).toLocalDate())
                .build();
    }
}
