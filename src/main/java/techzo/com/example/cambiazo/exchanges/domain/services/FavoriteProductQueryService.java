package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.FavoriteProduct;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllFavoriteProductByUserId;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllFavoriteProductsQuery;

import java.util.List;

public interface FavoriteProductQueryService {

    List<FavoriteProduct>handle(GetAllFavoriteProductsQuery query);

    List<FavoriteProduct>handle(GetAllFavoriteProductByUserId query);
}
