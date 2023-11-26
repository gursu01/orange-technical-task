package gursu.orange.technicaltask.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRateRequestDto {
    @NotBlank(message = "Currency code must not be blank")
    private String currencyCode;
    @NotNull
    private int rate;
    @Min(value = 0, message = "Amount must be greater than or equal to zero")
    private BigDecimal conversionRate;
}
