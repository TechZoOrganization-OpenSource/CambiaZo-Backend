package techzo.com.example.cambiazo.donations.interfaces.rest.transform;


import techzo.com.example.cambiazo.donations.domain.model.commands.CreateProjectCommand;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.CreateProjectResource;

public class CreateProjectCommandFromResourceAssembler {
    public static CreateProjectCommand toCommandFromResource(CreateProjectResource resource) {
        return new CreateProjectCommand(resource.name(), resource.description());
    }
}
