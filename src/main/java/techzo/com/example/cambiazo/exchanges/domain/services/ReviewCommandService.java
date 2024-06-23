package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Review;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateReviewCommand;

import java.util.Optional;

public interface ReviewCommandService {

    Optional<Review>handle(CreateReviewCommand command);

    boolean handleDeleteReview(Long id);

}
