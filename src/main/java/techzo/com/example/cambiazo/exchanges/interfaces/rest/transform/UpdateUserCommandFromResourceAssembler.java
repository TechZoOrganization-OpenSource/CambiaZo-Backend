package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.commands.UpdateUserCommand;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.UpdateUserResource;

public class UpdateUserCommandFromResourceAssembler {

    public static UpdateUserCommand toCommandFromResource(Long userId, UpdateUserResource resource) {
        return new UpdateUserCommand(userId, resource.name(), resource.email(), resource.phone(), resource.password(), resource.profilePicture(), resource.membershipId());
    }
}
