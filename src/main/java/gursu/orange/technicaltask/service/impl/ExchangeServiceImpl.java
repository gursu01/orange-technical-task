package gursu.orange.technicaltask.service.impl;

import gursu.orange.technicaltask.dto.ExchangeRateRequestDto;
import gursu.orange.technicaltask.dto.ExchangeRateResponseDto;
import gursu.orange.technicaltask.enities.ExchangeRate;
import gursu.orange.technicaltask.exceptions.ExchangeCurrencyException;
import gursu.orange.technicaltask.mappers.ExchangeRateMapper;
import gursu.orange.technicaltask.repository.CurrencyDictionaryRepository;
import gursu.orange.technicaltask.repository.ExchangeRateRepository;
import gursu.orange.technicaltask.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final CurrencyDictionaryRepository currencyDictionaryRepository;

    @Override
    @Transactional
    public Mono<ExchangeRate> saveExchangeRate(ExchangeRateRequestDto exchangeRateDTO) {
        return currencyDictionaryRepository
                .findCurrencyDictionaryByCode(exchangeRateDTO.getCurrencyCode())
                .flatMap(currencyDictionary -> {
                    ExchangeRate exchangeRate = ExchangeRateMapper.toEntity(exchangeRateDTO);
                    exchangeRate.setCurrencyDictionaryId(currencyDictionary.getPk());
                    return exchangeRateRepository.save(exchangeRate);
                })
                .switchIfEmpty(Mono.error(() -> ExchangeCurrencyException.currencyDictionaryNotFound(
                        String.format("Currency with code [%s] was not found", exchangeRateDTO.getCurrencyCode())
                )));
    }


    @Transactional(readOnly = true)
    public Mono<ExchangeRateResponseDto> getExchangeRateBayCurrencyCodeAndDate(String currencyCode, LocalDate date){
        return exchangeRateRepository.findLastExchangeRateForDate(currencyCode, date)
                .flatMap(exchangeRate -> Mono.just(ExchangeRateMapper.toResponseDto(exchangeRate)))
                .switchIfEmpty(Mono.error(() -> ExchangeCurrencyException.exchangeRateNotFound(
                        String.format("Exchange rate for currency code [%s] and date [%s] was not found", currencyCode,
                                date)
                )));
    }
}
