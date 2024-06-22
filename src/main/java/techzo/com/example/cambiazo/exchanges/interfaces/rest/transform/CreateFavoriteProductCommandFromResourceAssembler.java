package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateFavoriteProductCommand;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateFavoriteProductResource;

public class CreateFavoriteProductCommandFromResourceAssembler {

    public static CreateFavoriteProductCommand toCommandFromResource(CreateFavoriteProductResource resource) {
        return new CreateFavoriteProductCommand(resource.userId(), resource.productId());
    }
}
