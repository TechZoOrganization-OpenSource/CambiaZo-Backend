package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.FavoriteProduct;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.FavoriteProductResource;

public class FavoriteProductFromEntityAssembler {

    public static FavoriteProductResource toResourceFromEntity(FavoriteProduct favoriteProduct){
        return new FavoriteProductResource(favoriteProduct.getId(), favoriteProduct.getProductId(), favoriteProduct.getUserId());
    }


}
