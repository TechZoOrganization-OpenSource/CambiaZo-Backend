package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Benefit;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.BenefitResource;

public class BenefitResourceFromEntityAssembler {

    public static BenefitResource toResourceFromEntity(Benefit entity) {
        return new BenefitResource(entity.getId(), entity.getDescription(), entity.getMembershipId());
    }
}
