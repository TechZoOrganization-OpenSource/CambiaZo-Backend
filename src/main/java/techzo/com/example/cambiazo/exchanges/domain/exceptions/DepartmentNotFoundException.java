package techzo.com.example.cambiazo.exchanges.domain.exceptions;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(Long id) {
        super("Department with id " + id + " not found");
    }
}
