package techzo.com.example.cambiazo.donations.interfaces.rest.transform;

import techzo.com.example.cambiazo.donations.domain.model.commands.UpdateCategoryOngCommand;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.UpdateCategoryOngResource;

public class UpdateCategoryOngCommandFromResourceAssembler {

    public static UpdateCategoryOngCommand toCommandFromResource(Long categoryId, UpdateCategoryOngResource resource) {
        return new UpdateCategoryOngCommand(categoryId, resource.name());
    }
}
