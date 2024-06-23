package techzo.com.example.cambiazo.donations.interfaces.rest.transform;

import techzo.com.example.cambiazo.donations.domain.model.aggregates.SocialNetwork;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.SocialNetworkResource;

public class SocialNetworkResourceFromEntityAssembler {
    public static SocialNetworkResource toResourceFromEntity(SocialNetwork entity) {
        return new SocialNetworkResource(entity.getId(), entity.getName(), entity.getUrl(), entity.getOngId());
    }
}
