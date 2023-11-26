package gursu.orange.technicaltask.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRateResponseDto {
    @NotNull
    private String currencyCode;
    @NotNull
    private int rate;
    @NotNull
    private BigDecimal conversionRate;
    @NotNull
    private LocalDate localDate;
}
