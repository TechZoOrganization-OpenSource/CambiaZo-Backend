package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.commands.UpdateProductCategoryCommand;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.UpdateProductCategoryResource;

public class UpdateProductCategoryCommandFromResourceAssembler {

    public static UpdateProductCategoryCommand toCommandFromResource(Long categoryId, UpdateProductCategoryResource resource) {
        return new UpdateProductCategoryCommand(categoryId, resource.name());
    }
}
