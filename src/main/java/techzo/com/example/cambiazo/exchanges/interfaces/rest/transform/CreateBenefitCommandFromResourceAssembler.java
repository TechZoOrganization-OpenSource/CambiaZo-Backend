package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateBenefitCommand;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateBenefitResource;

public class CreateBenefitCommandFromResourceAssembler {

    public static CreateBenefitCommand toCommandFromResource(CreateBenefitResource resource) {
        return new CreateBenefitCommand(resource.description(), resource.membershipId());
    }
}
