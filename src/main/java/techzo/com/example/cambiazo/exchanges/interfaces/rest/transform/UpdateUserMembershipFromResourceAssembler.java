package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.commands.UpdateUserMembershipCommand;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.UpdateUserMembershipResource;

public class UpdateUserMembershipFromResourceAssembler {
    public static UpdateUserMembershipCommand toCommandFromResource(Long userId, UpdateUserMembershipResource resource) {
        return new UpdateUserMembershipCommand(userId, resource.membershipId());
    }
}
