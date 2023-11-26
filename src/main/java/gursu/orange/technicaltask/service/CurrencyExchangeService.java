package gursu.orange.technicaltask.service;

import gursu.orange.technicaltask.dto.CurrencyExchangeRequestDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface CurrencyExchangeService {

    Mono<Void> exchange(CurrencyExchangeRequestDto requestDto);

    Mono<ResponseEntity<Void>> validateRequest(CurrencyExchangeRequestDto requestDto);
}
