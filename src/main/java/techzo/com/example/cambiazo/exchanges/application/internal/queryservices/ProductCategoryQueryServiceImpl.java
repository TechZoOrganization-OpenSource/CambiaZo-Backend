package techzo.com.example.cambiazo.exchanges.application.internal.queryservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.ProductCategory;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllProductCategoryQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetProductCategoryByIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.services.ProductCategoryQueryService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.ProductCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryQueryServiceImpl implements ProductCategoryQueryService {
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryQueryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public List<ProductCategory> handle(GetAllProductCategoryQuery query) {
        return productCategoryRepository.findAll();
    }

    @Override
    public Optional<ProductCategory> handle(GetProductCategoryByIdQuery query) {
        return productCategoryRepository.findById(query.id());
    }
}
