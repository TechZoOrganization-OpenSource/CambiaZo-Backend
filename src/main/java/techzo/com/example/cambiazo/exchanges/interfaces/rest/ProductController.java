package techzo.com.example.cambiazo.exchanges.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Product;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.*;
import techzo.com.example.cambiazo.exchanges.domain.services.ProductCommandService;
import techzo.com.example.cambiazo.exchanges.domain.services.ProductQueryService;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateProductResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.ProductResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.UpdateProductResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Controller for managing Products.
 * Provides endpoints for creating, retrieving, updating, and deleting Products.
 *
 * @version 1.0
 * @author CambiaZo - TechZo
 */
@RestController
@RequestMapping("/api/v1/products")
@Tag(name="Products", description="Products Management Endpoints")
public class ProductController {

    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    /**
     * Constructs a new ProductController.
     *
     * @param productCommandService the service to handle product commands
     * @param productQueryService the service to handle product queries
     */
    public ProductController(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

    /**
     * Creates a new Product with the input data.
     *
     * @param resource the resource containing product data for creation
     * @return the created product resources wrapped in a ResponseEntity
     */
    @Operation(summary="Create a new Product", description="Create a new Product with the input data.")
    @PostMapping
    public ResponseEntity<List<ProductResource>> createProduct(@RequestBody CreateProductResource resource) {
        try {
            var createProductCommand = CreateProductCommandFromResourceAssembler.toCommandFromResource(resource);
            var product = productCommandService.handle(createProductCommand);
            var productResource = product.stream().map(ProductResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.status(CREATED).body(productResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves all Products filtered by boost.
     *
     * @param boost the boost filter
     * @return a list of product resources wrapped in a ResponseEntity
     */
    @Operation(summary="Get all Products by Boost", description="Get all Products by Boost.")
    @GetMapping("/boost/{boost}")
    public ResponseEntity<List<ProductResource>> getBoostedProducts(@PathVariable Boolean boost) {
        try {
            var getBoostedProductsQuery = new GetAllProductsByBoostQuery(boost);
            var products = productQueryService.handle(getBoostedProductsQuery);
            var productResources = products.stream().map(ProductResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(productResources);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves all Products filtered by name.
     *
     * @param name the name filter
     * @return a list of product resources wrapped in a ResponseEntity
     */
    @Operation(summary="Get all Products by Name", description="Get all Products by Name.")
    @GetMapping("/name/{name}")
    public ResponseEntity<List<ProductResource>> getProductsByName(@PathVariable String name) {
        try {
            var getProductsByNameQuery = new GetAllProductsByNameQuery(name);
            var products = productQueryService.handle(getProductsByNameQuery);
            var productResources = products.stream().map(ProductResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(productResources);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves all Products by User id and availability.
     *
     * @param id the user ID
     * @param available the availability filter
     * @return a list of product resources wrapped in a ResponseEntity
     */
    @Operation(summary="Get all Products by User Id and Available", description="Get all Products by User Id and Available.")
    @GetMapping("/users/{id}/available/{available}")
    public ResponseEntity<List<ProductResource>> getAllProductsByUserIdAndAvailable(@PathVariable Long id, @PathVariable Boolean available) {
        try {
            var getAllProductsByUserIdAndAvailableQuery = new GetAllProductsByUserIdAndAvailableQuery(id, available);
            var products = productQueryService.handle(getAllProductsByUserIdAndAvailableQuery);
            var productResources = products.stream().map(ProductResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(productResources);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves all Products by User ID.
     *
     * @param id the user ID
     * @return a list of product resources wrapped in a ResponseEntity
     */
    @Operation(summary="Get all Products by User Id", description="Get all Products by User Id.")
    @GetMapping("/users/{id}")
    public ResponseEntity<List<ProductResource>> getAllProductsByUserId(@PathVariable Long id) {
        try {
            var getAllProductsByUserIdQuery = new GetAllProductsByUserIdQuery(id);
            var products = productQueryService.handle(getAllProductsByUserIdQuery);
            var productResources = products.stream().map(ProductResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(productResources);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves a Product by its ID.
     *
     * @param id the product ID
     * @return the product resource wrapped in a ResponseEntity
     */
    @Operation(summary="Get Product By Id", description="Get Product By Id.")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResource> getProductById(@PathVariable Long id) {
        try {
            var getProductByIdQuery = new GetProductByIdQuery(id);
            var product = productQueryService.handle(getProductByIdQuery);
            var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
            return ResponseEntity.ok(productResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Retrieves all Products.
     *
     * @return a list of product resources wrapped in a ResponseEntity
     */
    @Operation(summary="Get all Products", description="Get all Products.")
    @GetMapping
    public ResponseEntity<List<ProductResource>> getAllProducts() {
        try {
            var getAllProductsQuery = new GetAllProductsQuery();
            var products = productQueryService.handle(getAllProductsQuery);
            var productResources = products.stream().map(ProductResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(productResources);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Updates a Product by its ID.
     *
     * @param productId the product ID
     * @param resource the resource containing product data for update
     * @return the updated product resource wrapped in a ResponseEntity
     */
    @Operation(summary="Edit a Product", description="Edit a Product by its id.")
    @PutMapping("/edit/{productId}")
    public ResponseEntity<ProductResource> updateProduct(@PathVariable Long productId, @RequestBody UpdateProductResource resource) {
        var updateProductCommand = UpdateProductCommandFromResourceAssembler.toCommandFromResource(productId, resource);
        var product = productCommandService.handle(updateProductCommand);
        if (product.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return ResponseEntity.ok(productResource);
    }

    /**
     * Deletes a Product by its ID.
     *
     * @param id the product ID
     * @return a ResponseEntity indicating the outcome of the operation
     */
    @Operation(summary="Delete a Product", description="Delete a Product by its id.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productCommandService.handleDeleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
