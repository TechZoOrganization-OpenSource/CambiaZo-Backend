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

@RestController
@RequestMapping("/api/v1/favorite-products")
@Tag(name="Favorite Products", description="Favorite Products Management Endpoints")
public class FavoriteProductController {

    private final FavoriteProductCommandService favoriteProductCommandService;

    private final FavoriteProductQueryService favoriteProductQueryService;

    public FavoriteProductController(FavoriteProductCommandService favoriteProductCommandService, FavoriteProductQueryService favoriteProductQueryService){
        this.favoriteProductCommandService=favoriteProductCommandService;
        this.favoriteProductQueryService=favoriteProductQueryService;
    }

    @Operation(summary="Create a new Favorite Product", description="Create a new Favorite Product with the input data.")
    @PostMapping
    public ResponseEntity<FavoriteProductResource>createFavoriteProduct(@RequestBody CreateFavoriteProductResource resource){
        try {
            var createFavoriteProductCommand= CreateFavoriteProductCommandFromResourceAssembler.toCommandFromResource(resource);
            var favoriteProduct=favoriteProductCommandService.handle(createFavoriteProductCommand);
            var favoriteProductResource= FavoriteProductFromEntityAssembler.toResourceFromEntity(favoriteProduct.get());
            return ResponseEntity.status(CREATED).body(favoriteProductResource);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary="Get all Favorite Products", description="Get all Favorite Products.")
    @GetMapping
    public ResponseEntity<List<FavoriteProductResource>>getAllFavoriteProducts(){
        try {
            var getAllFavoriteProducts = new GetAllFavoriteProductsQuery();
            var favoriteProducts = favoriteProductQueryService.handle(getAllFavoriteProducts);
            var favoriteProductResources = favoriteProducts.stream().map(FavoriteProductFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(favoriteProductResources);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary="Get all Favorite Products by User ID", description="Get all Favorite Products by User ID.")
    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoriteProductResource>>getAllFavoriteProductsByUserId(@PathVariable Long userId){
        try {
            var getAllFavoriteProductsByUserId = new GetAllFavoriteProductByUserId(userId);
            var favoriteProducts = favoriteProductQueryService.handle(getAllFavoriteProductsByUserId);
            var favoriteProductResources = favoriteProducts.stream().map(FavoriteProductFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(favoriteProductResources);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary="Delete a Favorite Product", description="Delete a Favorite Product by ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>deleteFavoriteProduct(@PathVariable Long id){
        try {
            favoriteProductCommandService.handleDeleteFavoriteProduct(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
