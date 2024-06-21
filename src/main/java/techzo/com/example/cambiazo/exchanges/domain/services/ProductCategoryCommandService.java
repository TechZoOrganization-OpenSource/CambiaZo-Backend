package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.ProductCategory;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateProductCategoryCommand;

import java.util.Optional;

public interface ProductCategoryCommandService {
    Optional<ProductCategory> handle(CreateProductCategoryCommand command);

    boolean handleDeleteProductCategory(Long id);
}
