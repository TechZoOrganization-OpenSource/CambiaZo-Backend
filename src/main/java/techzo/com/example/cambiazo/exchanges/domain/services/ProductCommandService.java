package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Product;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateProductCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.UpdateProductCommand;

import java.util.Optional;

public interface ProductCommandService {
    Optional<Product>handle(CreateProductCommand command);

    Optional<Product>handle(UpdateProductCommand command);

    boolean handleDeleteProduct(Long id);
}
