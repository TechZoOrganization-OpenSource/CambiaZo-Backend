package techzo.com.example.cambiazo.exchanges.domain.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super("User with id " + id + " not found");
    }
}
