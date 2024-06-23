package techzo.com.example.cambiazo.exchanges.domain.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super("Product with id " + productId + " not found");
    }
}
