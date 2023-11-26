package gursu.orange.technicaltask.service;

import gursu.orange.technicaltask.dto.ExchangeRateRequestDto;
import gursu.orange.technicaltask.dto.ExchangeRateResponseDto;
import gursu.orange.technicaltask.enities.ExchangeRate;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface ExchangeRateService {

    Mono<ExchangeRate> saveExchangeRate(ExchangeRateRequestDto exchangeRateDTO);

    Mono<ExchangeRateResponseDto> getExchangeRateBayCurrencyCodeAndDate(String currencyCode, LocalDate date);
}
