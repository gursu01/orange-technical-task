package gursu.orange.technicaltask.enities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Table("operators")
@ToString(exclude = {"currencyExchange", "foreignExchangeOffice"})
@EqualsAndHashCode(exclude = {"currencyExchange", "foreignExchangeOffice"})
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Operator extends AbstractEntity{

    @NotNull
    @Column("first_name")
    private String firstName;

    @NotNull
    @Column("last_name")
    private String lastName;

    @Column("operator")
    private CurrencyExchange currencyExchange;

    @Column("foreign_exchange_office_id")
    private ForeignExchangeOffice foreignExchangeOffice;
}

