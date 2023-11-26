package gursu.orange.technicaltask.enities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Table("exchange_rates")
@EqualsAndHashCode
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExchangeRate extends AbstractEntity {

    @NotNull
    @Column("currency_code")
    private String currencyCode;

    @NotNull
    @Column("rate")
    private int rate;

    @NotNull
    @Column("conversion_rate")
    private BigDecimal conversionRate;

    @Column("currency_id")
    private Long currencyDictionaryId;
}

