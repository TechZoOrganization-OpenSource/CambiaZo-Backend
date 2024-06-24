package techzo.com.example.cambiazo.exchanges.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.ProductCategory;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllProductCategoryQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetProductCategoryByIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.services.ProductCategoryCommandService;
import techzo.com.example.cambiazo.exchanges.domain.services.ProductCategoryQueryService;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateProductCategoryResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.ProductCategoryResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.UpdateProductCategoryResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.CreateProductCategoryCommandFromResourceAssembler;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.ProductCategoryResourceFromEntityAssembler;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.UpdateProductCategoryCommandFromResourceAssembler;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Controller for managing Product Categories.
 * Provides endpoints for creating, retrieving, updating, and deleting Product Categories.
 *
 * @author CambiaZo - TechZo
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/product-category")
@Tag(name = "Product Category", description = "Product Category Management Endpoints")
public class ProductCategoryController {
    private final ProductCategoryCommandService productCategoryCommandService;
    private final ProductCategoryQueryService productCategoryQueryService;

    /**
     * Constructs a new ProductCategoryController.
     *
     * @param productCategoryCommandService the service to handle product category commands
     * @param productCategoryQueryService the service to handle product category queries
     */
    public ProductCategoryController(ProductCategoryCommandService productCategoryCommandService, ProductCategoryQueryService productCategoryQueryService) {
        this.productCategoryCommandService = productCategoryCommandService;
        this.productCategoryQueryService = productCategoryQueryService;
    }

    /**
     * Creates a new Product Category with the input data.
     *
     * @param resource the resource containing product category data for creation
     * @return the created product category resource wrapped in a ResponseEntity
     */
    @Operation(summary = "Create a new Product Category", description = "Create a new Product Category with the input data.")
    @PostMapping
    public ResponseEntity<ProductCategoryResource> createProductCategory(@RequestBody CreateProductCategoryResource resource) {
        Optional<ProductCategory> productCategory = productCategoryCommandService.handle(CreateProductCategoryCommandFromResourceAssembler.toCommandFromResource(resource));
        return productCategory.map(source -> new ResponseEntity<>(ProductCategoryResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Retrieves all Product Categories.
     *
     * @return a list of product category resources wrapped in a ResponseEntity
     */
    @Operation(summary = "Get all Product Categories", description = "Get all Product Categories.")
    @GetMapping
    public ResponseEntity<List<ProductCategoryResource>> getAllProductCategory() {
        var getAllProductCategoryQuery = new GetAllProductCategoryQuery();
        var productCategory = productCategoryQueryService.handle(getAllProductCategoryQuery);
        var productCategoryResource = productCategory.stream().map(ProductCategoryResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(productCategoryResource);
    }

    /**
     * Retrieves a Product Category by its ID.
     *
     * @param id the product category ID
     * @return the product category resource wrapped in a ResponseEntity
     */
    @Operation(summary = "Get Product Category by ID", description = "Get Product Category by ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryResource> getProductCategoryById(@PathVariable Long id) {
        Optional<ProductCategory> productCategory = productCategoryQueryService.handle(new GetProductCategoryByIdQuery(id));
        return productCategory.map(source -> ResponseEntity.ok(ProductCategoryResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Updates a Product Category by its ID.
     *
     * @param productCategoryId the product category ID
     * @param resource the resource containing product category data for update
     * @return the updated product category resource wrapped in a ResponseEntity
     */
    @Operation(summary = "Edit Product Category", description = "Edit Product Category.")
    @PutMapping("/edit/{productCategoryId}")
    public ResponseEntity<ProductCategoryResource> updateProductCategory(@PathVariable Long productCategoryId, @RequestBody UpdateProductCategoryResource resource) {
        var updateProductCategoryCommand = UpdateProductCategoryCommandFromResourceAssembler.toCommandFromResource(productCategoryId, resource);
        var updateProductCategory = productCategoryCommandService.handle(updateProductCategoryCommand);
        if (updateProductCategory.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var productCategoryResource = ProductCategoryResourceFromEntityAssembler.toResourceFromEntity(updateProductCategory.get());
        return ResponseEntity.ok(productCategoryResource);
    }

    /**
     * Deletes a Product Category by its ID.
     *
     * @param id the product category ID
     * @return a ResponseEntity indicating the outcome of the operation
     */
    @Operation(summary = "Delete Product Category by ID", description = "Delete Product Category by ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteProductCategory(@PathVariable Long id) {
        try {
            productCategoryCommandService.handleDeleteProductCategory(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
