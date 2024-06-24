package techzo.com.example.cambiazo.donations.interfaces.rest.transform;

import techzo.com.example.cambiazo.donations.domain.model.commands.UpdateOngCommand;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.UpdateOngResource;

public class UpdateOngCommandFromResourceAssembler {

    public static UpdateOngCommand toCommandFromResource(Long ongId, UpdateOngResource resource) {
        return new UpdateOngCommand(ongId, resource.name(), resource.type(), resource.aboutUs(), resource.missionAndVision(), resource.supportForm(), resource.address(), resource.email(), resource.phone(), resource.logo(), resource.website(), resource.categoryOngId());
    }
}
