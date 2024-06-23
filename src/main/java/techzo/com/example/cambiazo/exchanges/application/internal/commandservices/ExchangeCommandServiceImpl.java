package techzo.com.example.cambiazo.exchanges.application.internal.commandservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.ProductNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Exchange;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateExchangeCommand;
import techzo.com.example.cambiazo.exchanges.domain.services.ExchangeCommandService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.ExchangeRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.ProductRepository;

import java.util.Optional;

@Service
public class ExchangeCommandServiceImpl implements ExchangeCommandService {

    private final ExchangeRepository exchangeRepository;

    private final ProductRepository productRepository;

    public ExchangeCommandServiceImpl(ExchangeRepository exchangeRepository, ProductRepository productRepository) {
        this.exchangeRepository = exchangeRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Exchange>handle(CreateExchangeCommand command) {
        var productOwn = productRepository.findById(command.productOwnId())
                .orElseThrow(() -> new ProductNotFoundException(command.productOwnId()));
        var productChange = productRepository.findById(command.productChangeId())
                .orElseThrow(() -> new ProductNotFoundException(command.productChangeId()));
        var exchange = new Exchange(command, productOwn, productChange);
        exchangeRepository.save(exchange);
        return Optional.of(exchange);
    }

    @Override
    public boolean handleDeleteExchange(Long id) {
        Optional<Exchange> exchange = exchangeRepository.findById(id);
        if (exchange.isPresent()) {
            exchangeRepository.delete(exchange.get());
            return true;
        } else {
            return false;
        }
    }
}
