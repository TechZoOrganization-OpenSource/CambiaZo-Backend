package techzo.com.example.cambiazo.exchanges.application.internal.commandservices;


import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.donations.domain.model.commands.UpdateCategoryOngCommand;
import techzo.com.example.cambiazo.donations.domain.model.entities.CategoryOng;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.ProductCategory;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateProductCategoryCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.UpdateProductCategoryCommand;
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
    public Optional<ProductCategory>handle(UpdateProductCategoryCommand command){
        if (productCategoryRepository.existsByNameAndIdIsNot(command.name(), command.id())) {
            throw new IllegalArgumentException("Product Category with same name already exists");
        }
        var result = productCategoryRepository.findById(command.id());

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Product Category does not exist");
        }
        var productCategoryToUpdate = result.get();
        try {
            var updatedProductCategory = productCategoryRepository.save(productCategoryToUpdate.updateInformation(command.name()));
            return Optional.of(updatedProductCategory);
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while updating product category: " + e.getMessage());
        }


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
