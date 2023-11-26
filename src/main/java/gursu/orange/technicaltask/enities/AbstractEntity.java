package gursu.orange.technicaltask.enities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@Table
@SuperBuilder
@NoArgsConstructor
public abstract class AbstractEntity {

    @Id
    private Long pk;

    @Version
    @NotNull
    private Integer version;

    @NotNull
    private Instant genDate;

    @NotNull
    private Instant modDate;

    @Override
    public int hashCode() {
        // different entity classes have the same PK, so hashCode() needs the class to prevent collisions
        Long thisPk = getPk();
        if (thisPk == null) {
            return super.hashCode();
        }

        return Objects.hash(getClass(), thisPk);
    }

    @Override
    public String toString() {
        if (getPk() == null) {
            return super.toString();
        }
        return getClass().getSimpleName() + "{pk=" + getPk() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(pk, that.pk);
    }
}
