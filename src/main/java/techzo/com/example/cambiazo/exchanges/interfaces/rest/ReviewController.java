package techzo.com.example.cambiazo.exchanges.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Review;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateReviewCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllReviewsByUserReceptorIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllReviewsQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetReviewByAuthorIdAndExchangeId;
import techzo.com.example.cambiazo.exchanges.domain.services.ReviewCommandService;
import techzo.com.example.cambiazo.exchanges.domain.services.ReviewQueryService;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateReviewResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.ReviewResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.CreateReviewCommandFromResourceAssembler;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.ExchangeResourceFromEntityAssembler;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.ReviewResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Controller for managing Reviews.
 * Provides endpoints for creating, retrieving, and deleting Reviews.
 *
 * @version 1.0
 * @author CambiaZo - TechZo
 */
@RestController
@RequestMapping("/api/v1/reviews")
@Tag(name = "Reviews", description = "Reviews Management Endpoints")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    /**
     * Constructs a new ReviewController.
     *
     * @param reviewCommandService the service to handle review commands
     * @param reviewQueryService the service to handle review queries
     */
    public ReviewController(ReviewCommandService reviewCommandService, ReviewQueryService reviewQueryService) {
        this.reviewCommandService = reviewCommandService;
        this.reviewQueryService = reviewQueryService;
    }

    /**
     * Creates a new Review with the input data.
     *
     * @param resource the resource containing review data for creation
     * @return the created review resource wrapped in a ResponseEntity
     */
    @Operation(summary = "Create a new Review", description = "Create a new Review with the input data.")
    @PostMapping
    public ResponseEntity<ReviewResource> createReview(@RequestBody CreateReviewResource resource) {
        try {
            var createReviewCommand = CreateReviewCommandFromResourceAssembler.toCommandFromResource(resource);
            var review = reviewCommandService.handle(createReviewCommand);
            var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(review.get());
            return ResponseEntity.status(CREATED).body(reviewResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves all Reviews.
     *
     * @return a list of review resources wrapped in a ResponseEntity
     */
    @Operation(summary = "Get all Reviews", description = "Get all Reviews.")
    @GetMapping
    public ResponseEntity<List<ReviewResource>> getAllReviews() {
        try {
            var getAllReviewsQuery = new GetAllReviewsQuery();
            var reviews = reviewQueryService.handle(getAllReviewsQuery);
            var reviewResources = reviews.stream().map(ReviewResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(reviewResources);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves Reviews by User receptor ID.
     *
     * @param id the user receptor ID
     * @return a list of review resources wrapped in a ResponseEntity
     */
    @Operation(summary = "Get Reviews by User receptor id", description = "Get Review by user receptor id.")
    @GetMapping("user-receptor/{id}")
    public ResponseEntity<List<ReviewResource>> getReviewsByUserReceptor(@PathVariable Long id) {
        try {
            var getAllReviewsByUserReceptorIdQuery = new GetAllReviewsByUserReceptorIdQuery(id);
            var reviews = reviewQueryService.handle(getAllReviewsByUserReceptorIdQuery);
            var reviewResources = reviews.stream().map(ReviewResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(reviewResources);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves a Review by Author ID and Exchange ID.
     *
     * @param authorId the author ID
     * @param exchangeId the exchange ID
     * @return the review resource wrapped in a ResponseEntity
     */
    @Operation(summary = "Get Review by Author ID and Exchange ID", description = "Get Review by author ID and exchange ID.")
    @GetMapping("/author/{authorId}/exchange/{exchangeId}")
    public ResponseEntity<ReviewResource> getReviewByAuthorIdAndExchangeId(@PathVariable Long authorId, @PathVariable Long exchangeId) {
        try {
            var query = new GetReviewByAuthorIdAndExchangeId(authorId, exchangeId);
            var review = reviewQueryService.handle(query);
            if (review.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(review.get());
            return ResponseEntity.ok(reviewResource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
    

    /**
     * Deletes a Review with the input data.
     *
     * @param id the review ID
     * @return a ResponseEntity indicating the outcome of the operation
     */
    @Operation(summary = "Delete a Review", description = "Delete a Review with the input data.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        try {
            reviewCommandService.handleDeleteReview(id);
            return ResponseEntity.ok("Review deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
