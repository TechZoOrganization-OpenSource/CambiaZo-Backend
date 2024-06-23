package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateExchangeCommand;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateExchangeResource;

public class CreateExchangeCommandFromResourceAssembler {

    public static CreateExchangeCommand toCommandFromResource(CreateExchangeResource resource) {
        return new CreateExchangeCommand(resource.productOwnId(), resource.productChangeId(), resource.status());
    }
}
