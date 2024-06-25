package techzo.com.example.cambiazo.exchanges.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllFavoriteProductByUserId;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllFavoriteProductsQuery;
import techzo.com.example.cambiazo.exchanges.domain.services.FavoriteProductCommandService;
import techzo.com.example.cambiazo.exchanges.domain.services.FavoriteProductQueryService;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateFavoriteProductResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.FavoriteProductResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.CreateFavoriteProductCommandFromResourceAssembler;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.FavoriteProductFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Controller class for managing Favorite Product operations through REST endpoints.
 *
 * @author CambiaZo - TechZo
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/favorite-products")
@Tag(name="Favorite Products", description="Favorite Products Management Endpoints")
public class FavoriteProductController {

    private final FavoriteProductCommandService favoriteProductCommandService;
    private final FavoriteProductQueryService favoriteProductQueryService;

    /**
     * Constructor for FavoriteProductController.
     *
     * @param favoriteProductCommandService Service handling commands related to Favorite Products.
     * @param favoriteProductQueryService Service handling queries related to Favorite Products.
     */
    public FavoriteProductController(FavoriteProductCommandService favoriteProductCommandService, FavoriteProductQueryService favoriteProductQueryService){
        this.favoriteProductCommandService = favoriteProductCommandService;
        this.favoriteProductQueryService = favoriteProductQueryService;
    }

    /**
     * Endpoint for creating a new Favorite Product.
     *
     * @param resource The resource containing data to create the Favorite Product.
     * @return ResponseEntity containing the created FavoriteProductResource.
     */
    @Operation(summary="Create a new Favorite Product", description="Create a new Favorite Product with the input data.")
    @PostMapping
    public ResponseEntity<FavoriteProductResource> createFavoriteProduct(@RequestBody CreateFavoriteProductResource resource){
        try {
            var createFavoriteProductCommand = CreateFavoriteProductCommandFromResourceAssembler.toCommandFromResource(resource);
            var favoriteProduct = favoriteProductCommandService.handle(createFavoriteProductCommand);
            var favoriteProductResource = FavoriteProductFromEntityAssembler.toResourceFromEntity(favoriteProduct.get());
            return ResponseEntity.status(CREATED).body(favoriteProductResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint for retrieving all Favorite Products.
     *
     * @return ResponseEntity containing a list of FavoriteProductResources.
     */
    @Operation(summary="Get all Favorite Products", description="Get all Favorite Products.")
    @GetMapping
    public ResponseEntity<List<FavoriteProductResource>> getAllFavoriteProducts(){
        try {
            var getAllFavoriteProducts = new GetAllFavoriteProductsQuery();
            var favoriteProducts = favoriteProductQueryService.handle(getAllFavoriteProducts);
            var favoriteProductResources = favoriteProducts.stream().map(FavoriteProductFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(favoriteProductResources);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint for retrieving all Favorite Products by User ID.
     *
     * @param userId The ID of the User whose Favorite Products to retrieve.
     * @return ResponseEntity containing a list of FavoriteProductResources.
     */
    @Operation(summary="Get all Favorite Products by User ID", description="Get all Favorite Products by User ID.")
    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoriteProductResource>> getAllFavoriteProductsByUserId(@PathVariable Long userId){
        try {
            var getAllFavoriteProductsByUserId = new GetAllFavoriteProductByUserId(userId);
            var favoriteProducts = favoriteProductQueryService.handle(getAllFavoriteProductsByUserId);
            var favoriteProductResources = favoriteProducts.stream().map(FavoriteProductFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(favoriteProductResources);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint for deleting a Favorite Product by its ID.
     *
     * @param id The ID of the Favorite Product to delete.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @Operation(summary="Delete a Favorite Product", description="Delete a Favorite Product by ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFavoriteProduct(@PathVariable Long id){
        try {
            favoriteProductCommandService.handleDeleteFavoriteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
