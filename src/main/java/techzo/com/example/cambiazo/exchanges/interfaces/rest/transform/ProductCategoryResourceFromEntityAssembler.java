package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.ProductCategory;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.ProductCategoryResource;

public class ProductCategoryResourceFromEntityAssembler {
    public static ProductCategoryResource toResourceFromEntity(ProductCategory entity) {
        return new ProductCategoryResource(entity.getId(), entity.getName());
    }
}
