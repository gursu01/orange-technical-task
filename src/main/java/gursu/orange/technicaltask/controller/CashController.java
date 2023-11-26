package gursu.orange.technicaltask.controller;

import gursu.orange.technicaltask.constants.ApiConstants;
import gursu.orange.technicaltask.dto.CashAmmountAdjustRequestDto;
import gursu.orange.technicaltask.service.CashService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.EXCHANGE_CASH_ENDPOINT)
public class CashController {

    private final CashService cashService;

    @PutMapping
    public Mono<ResponseEntity<Void>> adjustCashAmmount(@Validated @RequestBody CashAmmountAdjustRequestDto requestDto) {
        return cashService.adjustCashAmmount(requestDto)
                .then(Mono.fromCallable(() -> ResponseEntity.status(HttpStatus.OK).build()));
    }

}
