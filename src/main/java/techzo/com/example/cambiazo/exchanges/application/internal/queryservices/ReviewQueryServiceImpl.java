package techzo.com.example.cambiazo.exchanges.application.internal.queryservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.UserNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Review;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllReviewsByUserReceptorIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllReviewsQuery;
import techzo.com.example.cambiazo.exchanges.domain.services.ReviewQueryService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.ReviewRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.UserRepository;

import java.util.List;


@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;


    public ReviewQueryServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Review>handle(GetAllReviewsQuery query) {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review>handle(GetAllReviewsByUserReceptorIdQuery query) {
        User user = userRepository.findById(query.userReceptorId())
                .orElseThrow(() -> new UserNotFoundException(query.userReceptorId()));
        return reviewRepository.findByUserReceptorId(user);
    }

}
