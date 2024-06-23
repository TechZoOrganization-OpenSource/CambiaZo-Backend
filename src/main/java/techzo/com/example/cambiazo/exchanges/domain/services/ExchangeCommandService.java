package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Exchange;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateExchangeCommand;

import java.util.Optional;

public interface ExchangeCommandService {
    Optional<Exchange>handle(CreateExchangeCommand command);

    boolean handleDeleteExchange(Long id);
}
