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
import java.util.Currency;

@Getter
@Setter
@ToString
@Table("currency_dictionaries")
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CurrencyDictionary extends AbstractEntity {

    @NotNull
    @Column("currency")
    private Currency currency;

    @NotNull
    @Column("code")
    private String code;

    @NotNull
    @Column("numeric_code")
    private String numericCode;

    @NotNull
    @Column("origin_country")
    private String originCountry;

    @NotNull
    @Column("minor_unit")
    private int minorUnit;
}

