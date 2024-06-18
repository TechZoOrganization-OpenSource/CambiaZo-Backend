package techzo.com.example.cambiazo.donations.domain.exceptions;

public class CategoryOngNotFoundException extends RuntimeException{
    public CategoryOngNotFoundException(Long aLong) {
        super("Category Ong with id " + aLong + " not found");
    }
}
