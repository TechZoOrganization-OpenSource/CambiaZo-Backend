package techzo.com.example.cambiazo.exchanges.domain.exceptions;

public class CountryNotFoundException extends RuntimeException{
    public CountryNotFoundException(Long id) {
        super("Country with id " + id + " not found");
    }
}
