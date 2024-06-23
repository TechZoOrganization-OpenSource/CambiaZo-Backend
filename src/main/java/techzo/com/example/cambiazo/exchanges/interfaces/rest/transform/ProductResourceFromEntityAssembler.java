package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Product;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {

    public static ProductResource toResourceFromEntity(Product entity) {
        return new ProductResource(entity.getId(), entity.getName(), entity.getDescription(), entity.getDesiredObject() ,entity.getPrice(), entity.getImage(),entity.getBoost(), entity.getAvailable(), entity.getProductCategoryId(), entity.getUserId(), entity.getDistrictId());
    }
}
