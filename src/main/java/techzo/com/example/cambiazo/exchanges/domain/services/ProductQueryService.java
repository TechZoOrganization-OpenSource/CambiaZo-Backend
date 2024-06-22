package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Product;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ProductQueryService {

    List<Product> handle(GetAllProductsQuery query);

    List<Product> handle(GetAllProductsByBoostQuery query);

    List<Product> handle(GetAllProductsByUserIdAndAvailableQuery query);
    List<Product> handle(GetAllProductsByUserIdQuery query);
    Optional<Product>handle(GetProductByIdQuery query);

    List<Product>handle(GetAllProductsByNameQuery query);
}
