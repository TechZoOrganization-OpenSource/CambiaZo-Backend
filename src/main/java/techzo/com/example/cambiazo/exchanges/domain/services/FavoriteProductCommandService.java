package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.FavoriteProduct;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateFavoriteProductCommand;

import java.util.Optional;

public interface FavoriteProductCommandService {

    Optional<FavoriteProduct> handle(CreateFavoriteProductCommand command);

    boolean handleDeleteFavoriteProduct(Long id);
}
