package gursu.orange.technicaltask.mappers;

import gursu.orange.technicaltask.dto.CashAmmountAdjustRequestDto;
import gursu.orange.technicaltask.enities.Cash;

import java.math.RoundingMode;
import java.time.Instant;

public class CashMapper {

    public static Cash toEntity(CashAmmountAdjustRequestDto requestDto) {
        return Cash.builder()
                .currencyCode(requestDto.getCurrencyCode())
                .amount(requestDto.getAmmount().setScale(2, RoundingMode.HALF_EVEN))
                .genDate(Instant.now())
                .modDate(Instant.now())
                .build();
    }
}
