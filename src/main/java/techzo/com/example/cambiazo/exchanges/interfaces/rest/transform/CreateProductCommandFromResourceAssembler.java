package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateProductCommand;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {

    public static CreateProductCommand toCommandFromResource(CreateProductResource resource) {
        return new CreateProductCommand(resource.name(), resource.description(), resource.desiredObject(), resource.price(), resource.image(), resource.boost(), resource.available(), resource.productCategoryId(), resource.userId(), resource.districtId());
    }
}
