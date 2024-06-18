package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.ProductCategory;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllProductCategoryQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetProductCategoryByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryQueryService {
    List<ProductCategory> handle(GetAllProductCategoryQuery query);
    Optional<ProductCategory> handle(GetProductCategoryByIdQuery query);
}
