package gursu.orange.technicaltask.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CashAmmountAdjustRequestDto {

    @NotNull(message = "Operator ID must not be null")
    private Long operatorId;
    @NotBlank(message = "Currency code must not be blank")
    private String currencyCode;
    @NotNull
    @Min(value = 0, message = "Amount must be greater than or equal to zero")
    private BigDecimal amount;
    @NotNull
    private LocalDate date;
}
