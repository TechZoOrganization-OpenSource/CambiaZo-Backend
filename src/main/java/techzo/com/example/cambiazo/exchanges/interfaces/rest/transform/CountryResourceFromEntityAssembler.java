package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.entities.Country;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CountryResource;

public class CountryResourceFromEntityAssembler {

    public static CountryResource toResourceFromEntity(Country entity) {
        return new CountryResource(entity.getId(), entity.getName());
    }
}
