package techzo.com.example.cambiazo.donations.interfaces.rest.transform;

import techzo.com.example.cambiazo.donations.domain.model.commands.CreateSocialNetworkCommand;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.CreateSocialNetworkResource;

public class CreateSocialNetworkCommandFromResourceAssembler {

    public static CreateSocialNetworkCommand toCommandFromResource(CreateSocialNetworkResource resource) {
        return new CreateSocialNetworkCommand(resource.name(), resource.url(), resource.ongId());
    }
}
