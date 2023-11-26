package gursu.orange.technicaltask.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExchangeCurrencyException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final HttpStatus httpStatus;

    public ExchangeCurrencyException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public static ExchangeCurrencyException currencyDictionaryNotFound(String message) {
        return new ExchangeCurrencyException(HttpStatus.NOT_FOUND, message);
    }

    public static ExchangeCurrencyException exchangeRateNotFound(String message) {
        return new ExchangeCurrencyException(HttpStatus.NOT_FOUND, message);
    }

    public static ExchangeCurrencyException operatorNotFound(String message) {
        return new ExchangeCurrencyException(HttpStatus.NOT_FOUND, message);
    }

    public static ExchangeCurrencyException notSupportedCurrencyException(String message) {
        return new ExchangeCurrencyException(HttpStatus.BAD_REQUEST, message);
    }
}
