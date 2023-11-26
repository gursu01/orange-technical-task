package gursu.orange.technicaltask.controller;

import gursu.orange.technicaltask.constants.ApiConstants;
import gursu.orange.technicaltask.dto.CurrencyExchangeRequestDto;
import gursu.orange.technicaltask.service.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.EXCHANGE_CURRENCIES_ENDPOINT)
public class CurrencyExchangeController {

    private final CurrencyExchangeService currencyExchangeService;

    @PostMapping
    public Mono<ResponseEntity<Void>> performCurrencyExchange(@Validated @RequestBody CurrencyExchangeRequestDto requestDto) {
        Mono<ResponseEntity<Void>> invalidRequest = currencyExchangeService.validateRequest(requestDto);
        if (invalidRequest != null)
            return invalidRequest;
        return currencyExchangeService.exchange(requestDto)
                .then(Mono.fromCallable(() -> ResponseEntity.status(HttpStatus.CREATED).build()));
    }
}
