package gursu.orange.technicaltask.repository;

import gursu.orange.technicaltask.enities.CurrencyDictionary;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CurrencyDictionaryRepository extends ReactiveCrudRepository<CurrencyDictionary, Long> {

    Mono<CurrencyDictionary> findCurrencyDictionaryByCode(String code);
}
