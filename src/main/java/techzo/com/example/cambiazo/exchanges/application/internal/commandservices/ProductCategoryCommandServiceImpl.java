package techzo.com.example.cambiazo.exchanges.application.internal.commandservices;


import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.ProductCategory;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateProductCategoryCommand;
import techzo.com.example.cambiazo.exchanges.domain.services.ProductCategoryCommandService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.ProductCategoryRepository;

import java.util.Optional;

@Service
public class ProductCategoryCommandServiceImpl implements ProductCategoryCommandService {
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryCommandServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public Optional<ProductCategory> handle(CreateProductCategoryCommand command) {
        if (productCategoryRepository.existsByName(command.name())) {
            throw new IllegalArgumentException("Product category with same name already exists");
        }
        var productCategory = new ProductCategory(command);
        var createdProductCategory = productCategoryRepository.save(productCategory);
        return Optional.of(createdProductCategory);
    }

    @Override
    public boolean handleDeleteProductCategory(Long id) {
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(id);
        if (productCategory.isPresent()) {
            productCategoryRepository.delete(productCategory.get());
            return true;
        } else {
            return false;
        }
    }
}
