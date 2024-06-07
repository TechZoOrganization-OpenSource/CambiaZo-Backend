package techzo.com.example.cambiazo.donations.interfaces.rest.transform;

import techzo.com.example.cambiazo.donations.domain.model.commands.CreateCategoryOngCommand;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.CreateCategoryOngResource;

public class CreateCategoryOngCommandFromResourceAssembler {
    public static CreateCategoryOngCommand fromResource(CreateCategoryOngResource resource) {
        return new CreateCategoryOngCommand(resource.name());
    }
}
