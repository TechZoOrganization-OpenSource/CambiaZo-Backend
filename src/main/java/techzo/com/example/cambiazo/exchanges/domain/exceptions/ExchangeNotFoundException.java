package techzo.com.example.cambiazo.exchanges.domain.exceptions;

public class ExchangeNotFoundException extends RuntimeException{
    public ExchangeNotFoundException(Long id) {
        super("Exchange with id " + id + " not found.");
    }
}
