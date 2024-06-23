package techzo.com.example.cambiazo.exchanges.domain.model.commands;

public record CreateFavoriteProductCommand(Long productId, Long userId) {

    public CreateFavoriteProductCommand {
        if (productId == null) {
            throw new IllegalArgumentException("Product ID is mandatory");
        }
        if (userId == null) {
            throw new IllegalArgumentException("User ID is mandatory");
        }
    }
}
