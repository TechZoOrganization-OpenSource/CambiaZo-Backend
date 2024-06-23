package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateMembershipCommand;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateMembershipResource;

public class CreateMembershipCommandFromResourceAssembler {
    public static CreateMembershipCommand toCommandFromResource(CreateMembershipResource resource) {
        return new CreateMembershipCommand(resource.name(), resource.description(), resource.price());
    }
}
