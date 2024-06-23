package techzo.com.example.cambiazo.exchanges.interfaces.rest.resources;

public record CreateReviewResource(String message, Long score, String status, Long exchangeId, Long userAuthorId, Long userReceptorId) {

            public CreateReviewResource {
                if (message == null) {
                    throw new IllegalArgumentException("message cannot be null");
                }
                if (score == null) {
                    throw new IllegalArgumentException("score cannot be null");
                }
                if (status == null) {
                    throw new IllegalArgumentException("status cannot be null");
                }
                if (exchangeId == null) {
                    throw new IllegalArgumentException("exchangeId cannot be null");
                }
                if (userAuthorId == null) {
                    throw new IllegalArgumentException("userAuthorId cannot be null");
                }
                if (userReceptorId == null) {
                    throw new IllegalArgumentException("userReceptorId cannot be null");
                }
            }
}
