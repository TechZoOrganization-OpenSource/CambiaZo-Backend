package techzo.com.example.cambiazo.exchanges.application.internal.queryservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.ProductNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.UserNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Exchange;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Product;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.*;
import techzo.com.example.cambiazo.exchanges.domain.services.ExchangeQueryService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.ExchangeRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.ProductRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExchangeQueryServiceImpl implements ExchangeQueryService{

    private final ExchangeRepository exchangeRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    public ExchangeQueryServiceImpl(ExchangeRepository exchangeRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.exchangeRepository = exchangeRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Exchange>handle(GetExchangeByIdQuery query) {
        return exchangeRepository.findById(query.id());
    }

    @Override
    public List<Exchange> handle(GetAllExchangesQuery query){
        return exchangeRepository.findAll();
    }




    @Override
    public List<Exchange> handle(GetAllExchangesByProductOwnQuery query) {
        Product product = productRepository.findById(query.productOwnId())
                .orElseThrow(() -> new ProductNotFoundException(query.productOwnId()));
        return exchangeRepository.findAllExchangesByProductOwnId(product);
    }

    @Override
    public List<Exchange> handle(GetAllExchangesByProductChangeQuery query) {
        Product product = productRepository.findById(query.productChangeId())
                .orElseThrow(() -> new ProductNotFoundException(query.productChangeId()));
        return exchangeRepository.findAllExchangesByProductChangeId(product);
    }

    @Override
    public List<Exchange> handle(GetAllExchangesByUserOwnQuery query) {
        User user = userRepository.findById(query.userOwnId())
                .orElseThrow(() -> new UserNotFoundException(query.userOwnId()));
        return exchangeRepository.findAllByProductOwnId_UserId(user);
    }

    @Override
    public List<Exchange> handle(GetAllExchangesByUserChangeQuery query) {
        User user = userRepository.findById(query.userChangeId())
                .orElseThrow(() -> new UserNotFoundException(query.userChangeId()));
        return exchangeRepository.findAllByProductChangeId_UserId(user);
    }
}
