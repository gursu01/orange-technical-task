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

import java.util.List;

@Getter
@Setter
@Table("foreign_exchange_office")
@ToString(exclude = {"operators", "cash"})
@EqualsAndHashCode(exclude = {"operators", "cash"})
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ForeignExchangeOffice extends AbstractEntity{

    @Column("office_name")
    private String officeName;

    @Column("foreignExchangeOffice")
    private List<Operator> operators;

    @Column("foreignExchangeOffice")
    private List<Cash> cash;
}
