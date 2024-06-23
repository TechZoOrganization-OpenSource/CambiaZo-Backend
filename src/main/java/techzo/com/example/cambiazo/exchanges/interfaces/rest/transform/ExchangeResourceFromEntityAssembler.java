package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Exchange;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.ExchangeResource;

public class ExchangeResourceFromEntityAssembler {

    public static ExchangeResource toResourceFromEntity(Exchange entity) {
        return new ExchangeResource(entity.getId(), entity.getProductOwnId(), entity.getProductChangeId(), entity.getStatus());
    }
}
