package techzo.com.example.cambiazo.exchanges.application.internal.commandservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.DistrictNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.ProductCategoryNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.UserNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Product;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateProductCommand;
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
