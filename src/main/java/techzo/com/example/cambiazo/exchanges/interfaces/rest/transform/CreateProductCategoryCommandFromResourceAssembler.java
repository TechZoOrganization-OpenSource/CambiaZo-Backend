package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateProductCategoryCommand;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateProductCategoryResource;

public class CreateProductCategoryCommandFromResourceAssembler {
    public static CreateProductCategoryCommand toCommandFromResource(CreateProductCategoryResource resource) {
        return new CreateProductCategoryCommand(resource.name());
    }
}
