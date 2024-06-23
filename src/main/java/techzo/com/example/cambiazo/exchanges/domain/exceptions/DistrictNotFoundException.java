package techzo.com.example.cambiazo.exchanges.domain.exceptions;

public class DistrictNotFoundException extends RuntimeException{
    public DistrictNotFoundException(Long id) {
        super("District with id " + id + " not found");
    }
}
