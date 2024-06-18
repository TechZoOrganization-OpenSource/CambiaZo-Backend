package techzo.com.example.cambiazo.donations.interfaces.rest.transform;

import techzo.com.example.cambiazo.donations.domain.model.aggregates.Ong;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.OngResource;

public class OngResourceFromEntityAssembler {
    public static OngResource toResourceFromEntity(Ong entity){
        return new OngResource(
                entity.getId(),
                entity.getName(),
                entity.getType(),
                entity.getAboutUs(),
                entity.getMissionAndVision(),
                entity.getSupportForm(),
                entity.getAddress(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getLogo(),
                entity.getWebsite(),
                entity.getCategoryOngId()
        );
    }
}
