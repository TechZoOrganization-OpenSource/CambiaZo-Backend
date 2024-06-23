package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Product;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateProductCommand;

import java.util.Optional;

public interface ProductCommandService {
    Optional<Product>handle(CreateProductCommand command);

    boolean handleDeleteProduct(Long id);
}
