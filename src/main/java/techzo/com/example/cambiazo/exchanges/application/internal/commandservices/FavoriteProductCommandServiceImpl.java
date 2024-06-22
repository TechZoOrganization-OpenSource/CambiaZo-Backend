package techzo.com.example.cambiazo.exchanges.application.internal.commandservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.ProductNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.UserNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.FavoriteProduct;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Product;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateFavoriteProductCommand;
import techzo.com.example.cambiazo.exchanges.domain.services.FavoriteProductCommandService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.FavoriteProductRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.ProductRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.UserRepository;

import java.util.Optional;

@Service
public class FavoriteProductCommandServiceImpl implements FavoriteProductCommandService {

    private final FavoriteProductRepository favoriteProductRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    public FavoriteProductCommandServiceImpl(FavoriteProductRepository favoriteProductRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.favoriteProductRepository = favoriteProductRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Optional<FavoriteProduct>handle(CreateFavoriteProductCommand command) {
        User user = userRepository.findById(command.userId()).orElseThrow(() -> new UserNotFoundException(command.userId()));
        Product product = productRepository.findById(command.productId()).orElseThrow(() -> new ProductNotFoundException(command.productId()));
        var favoriteProduct = new FavoriteProduct(product, user);
        favoriteProductRepository.save(favoriteProduct);
        return Optional.of(favoriteProduct);
    }

    @Override
    public boolean handleDeleteFavoriteProduct(Long id) {
        Optional<FavoriteProduct> favoriteProduct = favoriteProductRepository.findById(id);
        if (favoriteProduct.isPresent()) {
            favoriteProductRepository.delete(favoriteProduct.get());
            return true;
        } else {
            return false;
        }
    }
}
