package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Exchange;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateExchangeCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.UpdateExchangeStatusCommand;

import java.util.Optional;

public interface ExchangeCommandService {
    Optional<Exchange>handle(CreateExchangeCommand command);

    Optional<Exchange>handle(UpdateExchangeStatusCommand command);

    boolean handleDeleteExchange(Long id);
}
