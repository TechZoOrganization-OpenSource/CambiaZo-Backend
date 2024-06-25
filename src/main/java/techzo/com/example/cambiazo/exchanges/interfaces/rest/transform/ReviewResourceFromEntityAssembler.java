package techzo.com.example.cambiazo.exchanges.interfaces.rest.transform;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Review;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.ReviewResource;

public class ReviewResourceFromEntityAssembler {

    public static ReviewResource toResourceFromEntity(Review entity) {
        return new ReviewResource(entity.getId(), entity.getMessage(), entity.getScore(), entity.getStatus(), entity.getExchangeId(),entity.getUserAuthorId(),entity.getUserReceptorId());
    }
}
