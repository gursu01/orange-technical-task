package gursu.orange.technicaltask.service.impl;

import gursu.orange.technicaltask.dto.CashAmmountAdjustRequestDto;
import gursu.orange.technicaltask.enities.Cash;
import gursu.orange.technicaltask.exceptions.ExchangeCurrencyException;
import gursu.orange.technicaltask.mappers.CashMapper;
import gursu.orange.technicaltask.repository.CashRepository;
import gursu.orange.technicaltask.repository.CurrencyDictionaryRepository;
import gursu.orange.technicaltask.repository.OperatorRepository;
import gursu.orange.technicaltask.service.CashService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class CashServiceImpl implements CashService {

    private final CashRepository cashRepository;
    private final CurrencyDictionaryRepository currencyDictionaryRepository;
    private final OperatorRepository operatorRepository;

    @Override
    @Transactional
    public Mono<Void> adjustCashAmmount(@Valid CashAmmountAdjustRequestDto requestDto) {
        return currencyDictionaryRepository
                .findCurrencyDictionaryByCode(requestDto.getCurrencyCode())
                .switchIfEmpty(Mono.error(() -> ExchangeCurrencyException.currencyDictionaryNotFound(
                        String.format("Currency with code [%s] was not found", requestDto.getCurrencyCode())
                )))
                .flatMap(currencyDictionary ->
                        operatorRepository.existsById(requestDto.getOperatorId())
                                .flatMap(operatorExists -> saveIfOperatorExists(requestDto, operatorExists)))
                .then();
    }

    private Mono<Void> saveIfOperatorExists(CashAmmountAdjustRequestDto requestDto, Boolean operatorExists) {
        if (operatorExists) {
            Cash cash = CashMapper.toEntity(requestDto);
            cash.setOperatorId(requestDto.getOperatorId());
            return cashRepository.save(cash).then();
        } else {
            return Mono.error(() -> ExchangeCurrencyException.operatorNotFound(
                    String.format("Operator with ID [%s] was not found", requestDto.getOperatorId())));
        }
    }
}
