package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateReviewCommand;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateReviewResource;

public class CreateReviewCommandFromResourceAssembler {

    public static CreateReviewCommand toCommandFromResource(CreateReviewResource resource) {
        return new CreateReviewCommand(resource.message(), resource.score(), resource.status(), resource.exchangeId(), resource.userAuthorId(), resource.userReceptorId());
    }
}
