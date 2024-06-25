package techzo.com.example.cambiazo.exchanges.domain.model.commands;

public record UpdateExchangeCommand(Long id, Long productOwnId, Long productChangeId, String status) {
}
