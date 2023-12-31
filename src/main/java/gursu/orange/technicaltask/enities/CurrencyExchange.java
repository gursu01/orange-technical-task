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

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Table("currency_exchange")
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CurrencyExchange extends AbstractEntity {

    @Column("source_currency_code")
    private String sourceCurrencyCode;

    @Column("target_currency_code")
    private String targetCurrencyCode;

    @Column("received_amount")
    private BigDecimal receivedAmount;

    @Column("released_amount")
    private BigDecimal releasedAmount;

    @Column("operator_id")
    private Long operatorId;

    @Column("exchange_rate_id")
    private Long exchangeRateId;
}

