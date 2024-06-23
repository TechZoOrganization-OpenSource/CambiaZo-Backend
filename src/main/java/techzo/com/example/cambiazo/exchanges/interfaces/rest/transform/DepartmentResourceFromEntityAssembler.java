package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Department;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.DepartmentResource;

public class DepartmentResourceFromEntityAssembler {

    public static DepartmentResource toResourceFromEntity(Department entity) {
        return new DepartmentResource(entity.getId(), entity.getName(), entity.getCountryId());
    }
}
