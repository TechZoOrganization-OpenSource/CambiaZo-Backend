package techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Exchange;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Review;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review>findByUserReceptorId(User id);

    Optional<Review>findReviewByUserAuthorIdAndExchangeId(User userAuthorId, Exchange exchangeId);
}
