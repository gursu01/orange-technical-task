package gursu.orange.technicaltask.service;

import gursu.orange.technicaltask.dto.CashAmmountAdjustRequestDto;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public interface CashService {

    Mono<Void> adjustCashAmmount(@Valid CashAmmountAdjustRequestDto requestDto);
}
