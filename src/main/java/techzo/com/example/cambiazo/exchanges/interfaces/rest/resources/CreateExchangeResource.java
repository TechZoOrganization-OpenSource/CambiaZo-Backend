package techzo.com.example.cambiazo.exchanges.interfaces.rest.resources;

public record CreateExchangeResource(Long productOwnId, Long productChangeId, String status) {

        public CreateExchangeResource {
            if (productOwnId == null) {
                throw new IllegalArgumentException("productOwnId cannot be null");
            }
            if (productChangeId == null) {
                throw new IllegalArgumentException("productChangeId cannot be null");
            }
            if (status == null) {
                throw new IllegalArgumentException("status cannot be null");
            }
        }
}
