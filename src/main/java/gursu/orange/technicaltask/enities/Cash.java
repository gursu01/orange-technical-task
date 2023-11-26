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
@Table("cash")
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Cash extends AbstractEntity {

    @NotNull
    @Column("currency_code")
    private String currencyCode;

    @NotNull
    @Column("ammount")
    private BigDecimal ammount;

    @Column("operator_id")
    private Long operatorId;

    @Column("foreign_exchange_office_id")
    private Long foreignExchangeOfficeId;
}
