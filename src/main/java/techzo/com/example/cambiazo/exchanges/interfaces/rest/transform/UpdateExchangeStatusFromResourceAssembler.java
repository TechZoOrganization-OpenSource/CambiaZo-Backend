package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.commands.UpdateExchangeStatusCommand;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.UpdateExchangeStatusResource;

public class UpdateExchangeStatusFromResourceAssembler {

    public static UpdateExchangeStatusCommand toCommandFromResource(Long exchangeId, UpdateExchangeStatusResource resource) {
        return new UpdateExchangeStatusCommand(exchangeId, resource.status());
    }
}
