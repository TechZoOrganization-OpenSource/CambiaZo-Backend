package techzo.com.example.cambiazo.donations.interfaces.rest.transform;

import techzo.com.example.cambiazo.donations.domain.model.aggregates.CategoryOng;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.CategoryOngResource;

public class CategoryOngResourceFromEntityAssembler {
    public static CategoryOngResource toResourceFromEntity(CategoryOng entity) {
        return new CategoryOngResource(entity.getId(), entity.getName());
    }
}
