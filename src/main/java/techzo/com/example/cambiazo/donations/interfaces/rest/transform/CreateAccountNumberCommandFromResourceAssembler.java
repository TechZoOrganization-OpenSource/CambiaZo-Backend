package techzo.com.example.cambiazo.donations.interfaces.rest.transform;

import techzo.com.example.cambiazo.donations.domain.model.commands.CreateAccountNumberCommand;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.CreateAccountNumberResource;

public class CreateAccountNumberCommandFromResourceAssembler {

    public static CreateAccountNumberCommand toCommandFromResource(CreateAccountNumberResource resource) {
        return new CreateAccountNumberCommand(resource.name(), resource.cci(), resource.account(), resource.ongId());
    }
}
