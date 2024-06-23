package techzo.com.example.cambiazo.exchanges.application.internal.queryservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Product;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.*;
import techzo.com.example.cambiazo.exchanges.domain.services.ProductQueryService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.ProductRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public ProductQueryServiceImpl(ProductRepository productRepository, UserRepository userRepository){
        this.productRepository=productRepository;
        this.userRepository=userRepository;
    }

    @Override
    public List<Product>handle(GetAllProductsQuery query){
        return productRepository.findAll();
    }

    @Override
    public List<Product>handle(GetAllProductsByBoostQuery query){
        return productRepository.findByBoost(query.boost());
    }

    @Override
    public List<Product>handle(GetAllProductsByUserIdAndAvailableQuery query){
        User user = userRepository.findById(query.userId())
                .orElseThrow(() -> new IllegalArgumentException("User with id " + query.userId() + " not found"));

        return productRepository.findAllByUserIdAndAvailable(user, query.available());
    }

    @Override
    public List<Product>handle(GetAllProductsByUserIdQuery query){
        User user = userRepository.findById(query.id())
                .orElseThrow(() -> new IllegalArgumentException("User with id " + query.id() + " not found"));
        return productRepository.findAllByUserId(user);
    }

    @Override
    public Optional<Product>handle(GetProductByIdQuery query){
        return productRepository.findById(query.id());
    }

    @Override
    public List<Product>handle(GetAllProductsByNameQuery query){
        return productRepository.findAllProductsByName(query.name());
    }
}
