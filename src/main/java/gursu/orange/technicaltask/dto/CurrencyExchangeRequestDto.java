package gursu.orange.technicaltask.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CurrencyExchangeRequestDto {

    @NotBlank(message = "Target currency code must not be blank")
    private String targetCurrencyCode;
    @NotBlank(message = "Source currency code must not be blank")
    private String sourceCurrencyCode;
    @Min(value = 0, message = "Amount must be greater than or equal to zero")
    private BigDecimal conversionRate;
    @NotNull
    private int rate;
    @Min(value = 0, message = "Received amount must be greater than or equal to zero")
    private BigDecimal receivedAmount;
    @Min(value = 0, message = "Released amount must be greater than or equal to zero")
    private BigDecimal releasedAmount;
    @NotNull
    private Long operatorId;
}
