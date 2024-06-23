package techzo.com.example.cambiazo.exchanges.domain.model.commands;

public record CreateReviewCommand(String message, Long score, String status, Long exchangeId, Long userAuthorId, Long userReceptorId) {
}
