package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateCountryCommand;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateCountryResource;

public class CreateCountryCommandFromResourceAssembler {

    public static CreateCountryCommand toCommandFromResource(CreateCountryResource resource) {
        return new CreateCountryCommand(resource.name());
    }
}
