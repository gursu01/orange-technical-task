package gursu.orange.technicaltask.service.impl;

import gursu.orange.technicaltask.dto.CurrencyExchangeRequestDto;
import gursu.orange.technicaltask.enities.CurrencyExchange;
import gursu.orange.technicaltask.enities.ExchangeRate;
import gursu.orange.technicaltask.exceptions.ExchangeCurrencyException;
import gursu.orange.technicaltask.mappers.CurrencyExchangeMapper;
import gursu.orange.technicaltask.repository.CurrencyDictionaryRepository;
import gursu.orange.technicaltask.repository.CurrencyExchangeRepository;
import gursu.orange.technicaltask.repository.ExchangeRateRepository;
import gursu.orange.technicaltask.repository.OperatorRepository;
import gursu.orange.technicaltask.service.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static gursu.orange.technicaltask.constants.CurrencyCodeConstants.MDL;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {
    private final CurrencyExchangeRepository currencyExchangeRepository;
    private final CurrencyDictionaryRepository currencyDictionaryRepository;
    private final OperatorRepository operatorRepository;
    private final ExchangeRateRepository exchangeRateRepository;

    @Override
    @Transactional
    public Mono<Void> exchange(CurrencyExchangeRequestDto requestDto) {
        return currencyDictionaryRepository.findCurrencyDictionaryByCode(requestDto.getTargetCurrencyCode())
                .switchIfEmpty(Mono.error(() -> ExchangeCurrencyException.currencyDictionaryNotFound(
                        String.format("Currency with code [%s] was not found", requestDto.getTargetCurrencyCode()))))
                .flatMap(currencyDictionary -> operatorExists(requestDto.getOperatorId()).flatMap(
                        operatorExists -> addExchangeRateAndSave(requestDto, operatorExists))).then();
    }

    private Mono<Boolean> operatorExists(Long operatorId) {
        return operatorRepository.existsById(operatorId);
    }

    private Mono<Void> addExchangeRateAndSave(CurrencyExchangeRequestDto requestDto, Boolean operatorExists) {
        return operatorExists
                ? findAndAddExchangeRate(requestDto)
                : Mono.error(() -> ExchangeCurrencyException.operatorNotFound(
                        String.format("Operator with ID [%s] was not found", requestDto.getOperatorId())));
    }

    private Mono<Void> findAndAddExchangeRate(CurrencyExchangeRequestDto requestDto) {
        return exchangeRateRepository.findLastExchangeRateForDate(requestDto.getTargetCurrencyCode(), LocalDate.now())
                .switchIfEmpty(Mono.defer(() -> findMostRecentExchangeRate(requestDto)))
                .flatMap(exchangeRate -> saveExchange(requestDto, exchangeRate)).then();
    }

    private Mono<ExchangeRate> findMostRecentExchangeRate(CurrencyExchangeRequestDto requestDto) {
        return exchangeRateRepository.findTopByCurrencyCodeOrderByGenDateDesc(requestDto.getSourceCurrencyCode())
                .switchIfEmpty(Mono.error(() -> ExchangeCurrencyException.exchangeRateNotFound(
                        String.format("No exchange rate was found for [%s] currency code",
                                requestDto.getSourceCurrencyCode()))));
    }

    private Mono<Void> saveExchange(CurrencyExchangeRequestDto requestDto, ExchangeRate exchangeRate) {
        CurrencyExchange exchange = CurrencyExchangeMapper.toEntity(requestDto);
        exchange.setOperatorId(requestDto.getOperatorId());
        exchange.setExchangeRateId(exchangeRate.getPk());
        BigDecimal releasedAmount = requestDto.getReceivedAmount().multiply(exchangeRate.getConversionRate())
                .setScale(2, RoundingMode.HALF_EVEN);
        exchange.setReleasedAmount(releasedAmount);
        return currencyExchangeRepository.save(exchange).then();
    }

    public Mono<ResponseEntity<Void>> validateRequest(CurrencyExchangeRequestDto requestDto) {
        if (!requestDto.getTargetCurrencyCode().equalsIgnoreCase(MDL.name())) {
            return Mono.error(ExchangeCurrencyException.notSupportedCurrencyException(
                    String.format("Only exchange to target currency [%s] is currently supported!", MDL.name())));
        }
        return null;
    }

}
