package gursu.orange.technicaltask.repository;

import gursu.orange.technicaltask.enities.CurrencyExchange;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CurrencyExchangeRepository extends ReactiveCrudRepository<CurrencyExchange, Long> {
}
