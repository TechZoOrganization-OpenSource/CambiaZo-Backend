package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.entities.Membership;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.MembershipResource;

public class MembershipResourceFromEntityAssembler {

    public static MembershipResource toResourceFromEntity(Membership entity) {
        return new MembershipResource(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice());
    }
}
