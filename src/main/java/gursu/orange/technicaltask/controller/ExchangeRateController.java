package gursu.orange.technicaltask.controller;

import gursu.orange.technicaltask.constants.ApiConstants;
import gursu.orange.technicaltask.dto.ExchangeRateRequestDto;
import gursu.orange.technicaltask.dto.ExchangeRateResponseDto;
import gursu.orange.technicaltask.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.EXCHANGE_RATES_ENDPOINT)
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @PostMapping
    public Mono<ResponseEntity<Void>> createExchangeRate(@Validated @RequestBody ExchangeRateRequestDto exchangeRateDTO) {
        return exchangeRateService.saveExchangeRate(exchangeRateDTO)
                .map(savedExchangeRate -> ResponseEntity.status(HttpStatus.CREATED).build());
    }

    @GetMapping(path = "/currencies/{currencyCode}/dates/{date}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<ResponseEntity<ExchangeRateResponseDto>> getExchangeRate(@PathVariable String currencyCode,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return exchangeRateService.getExchangeRateBayCurrencyCodeAndDate(currencyCode, date)
                .map(ResponseEntity::ok);
    }
}
