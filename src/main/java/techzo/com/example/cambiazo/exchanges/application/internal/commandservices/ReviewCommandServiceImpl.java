package techzo.com.example.cambiazo.exchanges.application.internal.commandservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.ExchangeNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.UserNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Exchange;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Review;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateReviewCommand;
import techzo.com.example.cambiazo.exchanges.domain.services.ReviewCommandService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.ExchangeRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.ReviewRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.UserRepository;

import java.util.Optional;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final ExchangeRepository exchangeRepository;

    public ReviewCommandServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, ExchangeRepository exchangeRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.exchangeRepository = exchangeRepository;
    }

    @Override
    public Optional<Review>handle(CreateReviewCommand command) {
        User userAuthor = userRepository.findById(command.userAuthorId())
                .orElseThrow(() -> new UserNotFoundException(command.userAuthorId()));

        User userReceptor = userRepository.findById(command.userReceptorId())
                .orElseThrow(() -> new UserNotFoundException(command.userReceptorId()));

        Exchange exchange = exchangeRepository.findById(command.exchangeId())
                .orElseThrow(() -> new ExchangeNotFoundException(command.exchangeId()));

        var review = new Review(command,exchange, userAuthor, userReceptor);
        reviewRepository.save(review);
        return Optional.of(review);
    }

    @Override
    public boolean handleDeleteReview(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isPresent()) {
            reviewRepository.delete(review.get());
            return true;
        } else {
            return false;
        }
    }
}
