package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.District;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.DistrictResource;

public class DistrictResourceFromEntityAssembler {

    public static DistrictResource toResourceFromEntity(District entity) {
        return new DistrictResource(entity.getId(), entity.getName(), entity.getDepartmentId());
    }
}
