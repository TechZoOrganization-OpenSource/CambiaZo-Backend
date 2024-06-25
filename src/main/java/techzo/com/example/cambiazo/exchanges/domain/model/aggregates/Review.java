package techzo.com.example.cambiazo.exchanges.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateReviewCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.valueobjects.ReviewScore;
import techzo.com.example.cambiazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

/**
 * Represents a Review in the exchange system.
 *
 * @author CambiaZo - TechZo
 * @version 1.0
 *
 */


@Setter
@Getter
@Entity
public class Review extends AuditableAbstractAggregateRoot<Review> {


    @Column(name = "message", columnDefinition = "TEXT")
    @NotNull(message = "Message is mandatory")
    @Getter
    private String message;

    @Embedded
    @Column(name = "score", nullable = false)
    @NotNull(message = "ReviewScore is mandatory")
    private ReviewScore score;

    @Column(name = "status", nullable = false)
    @NotNull(message = "Status is mandatory")
    private String status;

    @ManyToOne
    @JoinColumn(name = "exchange_id", nullable = false)
    @NotNull(message = "ExchangeId is mandatory")
    private Exchange exchangeId;

    @ManyToOne
    @JoinColumn(name = "user_author_id", nullable = false)
    @NotNull(message = "UserAuthorId is mandatory")
    private User userAuthorId;

    @ManyToOne
    @JoinColumn(name = "user_receptor_id", nullable = false)
    @NotNull(message = "UserReceptorId is mandatory")
    private User userReceptorId;

    public Review() {
    }

    public Review(CreateReviewCommand command, Exchange exchangeId, User userAuthorId, User userReceptorId) {
        this.message = command.message();
        this.score = new ReviewScore(command.score());
        this.status = command.status();
        this.exchangeId = exchangeId;
        this.userAuthorId = userAuthorId;
        this.userReceptorId = userReceptorId;
    }

    public Long getReviewId() {
        return getId();
    }

    public Long getExchangeId() {
        return exchangeId.getId();
    }

    public Long getUserAuthorId() {
        return userAuthorId.getId();
    }

    public Long getUserReceptorId() {
        return userReceptorId.getId();
    }

    public Long getScore() {
        return score.getReviewScore();
    }

}
