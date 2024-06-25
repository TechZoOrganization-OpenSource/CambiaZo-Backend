package techzo.com.example.cambiazo.exchanges.application.internal.commandservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.DistrictNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.ProductCategoryNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.UserNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.District;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Product;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.ProductCategory;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateProductCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.UpdateProductCommand;
import techzo.com.example.cambiazo.exchanges.domain.services.ProductCommandService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.DistrictRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.ProductCategoryRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.ProductRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.UserRepository;

import java.util.Optional;


@Service
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductRepository productRepository;

    private final ProductCategoryRepository productCategoryRepository;
    private final UserRepository userRepository;

    private final DistrictRepository districtRepository;


    public ProductCommandServiceImpl(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, UserRepository userRepository, DistrictRepository districtRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.userRepository = userRepository;
        this.districtRepository = districtRepository;
    }

    @Override
    public Optional<Product>handle(CreateProductCommand command) {
        var productCategory = productCategoryRepository.findById(command.productCategoryId())
                .orElseThrow(() -> new ProductCategoryNotFoundException(command.productCategoryId()));

        var user = userRepository.findById(command.userId())
                .orElseThrow(() -> new UserNotFoundException(command.userId()));

        var district = districtRepository.findById(command.districtId())
                .orElseThrow(() -> new DistrictNotFoundException(command.districtId()));

        var product = new Product(command, productCategory, user, district);
        productRepository.save(product);
        return Optional.of(product);

    }

    @Override
    public Optional<Product> handle(UpdateProductCommand command) {
        if(productRepository.existsByNameAndId(command.name(), command.id())) {
            throw new IllegalArgumentException("Product with same name already exists");
        }
        var result = productRepository.findById(command.id());

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Product does not exist");
        }

        var productToUpdate = result.get();
        try{
            ProductCategory productCategory = productCategoryRepository.findById(command.productCategoryId())
                    .orElseThrow(() -> new ProductCategoryNotFoundException(command.productCategoryId()));

            User user = userRepository.findById(command.userId())
                    .orElseThrow(() -> new UserNotFoundException(command.userId()));

            District district = districtRepository.findById(command.districtId())
                    .orElseThrow(() -> new DistrictNotFoundException(command.districtId()));

            var updatedProduct = productRepository.save(productToUpdate.updateInformation(command.name(), command.description(), command.desiredObject(), command.price(), command.image(), command.boost(), command.available(), productCategory, user, district));
            return Optional.of(updatedProduct);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating product: " + e.getMessage());
        }
    }

    @Override
    public boolean handleDeleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            return true;
        } else {
            return false;
        }
    }

}
