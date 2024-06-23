package techzo.com.example.cambiazo.exchanges.domain.model.commands;

public record CreateExchangeCommand(Long productOwnId, Long productChangeId, String status) {
}
