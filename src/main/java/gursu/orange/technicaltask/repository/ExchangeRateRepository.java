package gursu.orange.technicaltask.repository;

import gursu.orange.technicaltask.enities.ExchangeRate;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public interface ExchangeRateRepository extends ReactiveCrudRepository<ExchangeRate, Long> {

    Mono<ExchangeRate> findTopByCurrencyCodeOrderByGenDateDesc(String currencyCode);
    @Query( "SELECT * FROM exchange_rates " +
            "WHERE currency_code = :currencyCode AND gen_date >= :startDate AND gen_date < :endDate " +
            "ORDER BY gen_date DESC LIMIT 1")
    Mono<ExchangeRate> findLastExchangeRateForDate(
            @Param("currencyCode") String currencyCode,
            @Param("startDate") Instant startDate,
            @Param("endDate") Instant endDate
    );

    default Mono<ExchangeRate> findLastExchangeRateForDate(String currencyCode, LocalDate localDate) {
        Instant startOfDay = localDate.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant endOfDay = localDate.plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC);
        return findLastExchangeRateForDate(currencyCode, startOfDay, endOfDay);
    }
}
