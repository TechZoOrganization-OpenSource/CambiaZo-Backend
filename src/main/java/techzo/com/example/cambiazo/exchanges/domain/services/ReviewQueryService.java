package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Review;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllReviewsByUserReceptorIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllReviewsQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetReviewByAuthorIdAndExchangeId;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {

    List<Review> handle(GetAllReviewsQuery query);

    List<Review>handle(GetAllReviewsByUserReceptorIdQuery query);

    Optional<Review> handle(GetReviewByAuthorIdAndExchangeId query);
}
