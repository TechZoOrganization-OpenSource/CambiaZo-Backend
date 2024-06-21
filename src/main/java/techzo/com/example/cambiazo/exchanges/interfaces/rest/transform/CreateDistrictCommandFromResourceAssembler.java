package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateDistrictCommand;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateDistrictResource;

public class CreateDistrictCommandFromResourceAssembler {

    public static CreateDistrictCommand toCommandFromResource(CreateDistrictResource districtResource) {
        return new CreateDistrictCommand(districtResource.name(), districtResource.departmentId());
    }
}
