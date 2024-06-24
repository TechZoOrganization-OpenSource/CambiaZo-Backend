package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.ProductCategory;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateProductCategoryCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.UpdateProductCategoryCommand;

import java.util.Optional;

public interface ProductCategoryCommandService {
    Optional<ProductCategory> handle(CreateProductCategoryCommand command);

    Optional<ProductCategory> handle(UpdateProductCategoryCommand command);
    boolean handleDeleteProductCategory(Long id);
}
