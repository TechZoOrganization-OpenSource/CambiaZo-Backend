package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateDepartmentCommand;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateDepartmentResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.DepartmentResource;

public class CreateDepartmentCommandFromResourceAssembler {
    public static CreateDepartmentCommand toCommandFromResource(CreateDepartmentResource departmentResource) {
        return new CreateDepartmentCommand(departmentResource.name(), departmentResource.countryId());
    }
}
