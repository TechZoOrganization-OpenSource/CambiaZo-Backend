package techzo.com.example.cambiazo.exchanges.interfaces.rest.resources;

public record CreateFavoriteProductResource(Long productId, Long userId) {

        public CreateFavoriteProductResource {
            if (productId == null) {
                throw new IllegalArgumentException("productId cannot be null");
            }
            if (userId == null) {
                throw new IllegalArgumentException("userId cannot be null");
            }
        }
}
