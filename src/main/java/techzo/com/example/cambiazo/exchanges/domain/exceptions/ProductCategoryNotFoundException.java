package techzo.com.example.cambiazo.exchanges.domain.exceptions;

public class ProductCategoryNotFoundException extends RuntimeException{
    public ProductCategoryNotFoundException(Long id) {
        super("Product category with id " + id + " not found");
    }
}
