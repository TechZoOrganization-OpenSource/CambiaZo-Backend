package techzo.com.example.cambiazo.donations.interfaces.rest.transform;

import techzo.com.example.cambiazo.donations.domain.model.commands.CreateScheduleCommand;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.CreateScheduleResource;

public class CreateScheduleCommandFromResourceAssembler {

    public static CreateScheduleCommand toCommandFromResource(CreateScheduleResource resource) {
        return new CreateScheduleCommand(resource.text(), resource.ongId());
    }
}
