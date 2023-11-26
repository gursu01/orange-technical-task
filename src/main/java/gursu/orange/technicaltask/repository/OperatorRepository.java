package gursu.orange.technicaltask.repository;

import gursu.orange.technicaltask.enities.Operator;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OperatorRepository extends ReactiveCrudRepository<Operator, Long> {
}
