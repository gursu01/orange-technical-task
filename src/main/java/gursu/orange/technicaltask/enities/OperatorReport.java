package gursu.orange.technicaltask.enities;

import gursu.orange.technicaltask.enities.enums.ReportType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@Table("operator_report")
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OperatorReport extends AbstractEntity{

    @Column("report_type")
    private ReportType reportType;

    @Column("number_of_transactions")
    private int numberOfTransactions;

    @Column("currency_code")
    private String currencyCode;

    @Column("operator_id")
    private Operator operator;
}
