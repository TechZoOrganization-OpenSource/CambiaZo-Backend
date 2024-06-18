package techzo.com.example.cambiazo.exchanges.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.ProductCategory;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllProductCategoryQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetProductCategoryByIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.services.ProductCategoryCommandService;
import techzo.com.example.cambiazo.exchanges.domain.services.ProductCategoryQueryService;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateProductCategoryResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.ProductCategoryResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.CreateProductCategoryCommandFromResourceAssembler;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.ProductCategoryResourceFromEntityAssembler;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/product-category")
public class ProductCategoryController {
    private final ProductCategoryCommandService productCategoryCommandService;

    private final ProductCategoryQueryService productCategoryQueryService;

    public ProductCategoryController(ProductCategoryCommandService productCategoryCommandService, ProductCategoryQueryService productCategoryQueryService) {
        this.productCategoryCommandService = productCategoryCommandService;
        this.productCategoryQueryService = productCategoryQueryService;
    }

    @PostMapping
    public ResponseEntity<ProductCategoryResource> createProductCategory(@RequestBody CreateProductCategoryResource resource) {
        Optional<ProductCategory> productCategory = productCategoryCommandService.handle(CreateProductCategoryCommandFromResourceAssembler.toCommandFromResource(resource));
        return productCategory.map(source -> new ResponseEntity<>(ProductCategoryResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductCategoryResource>> getAllProductCategory() {
        var getAllProductCategoryQuery = new GetAllProductCategoryQuery();
        var productCategory = productCategoryQueryService.handle(getAllProductCategoryQuery);
        var productCategoryResource = productCategory.stream().map(ProductCategoryResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(productCategoryResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryResource> getProductCategoryById(@PathVariable Long id) {
        Optional<ProductCategory> productCategory = productCategoryQueryService.handle(new GetProductCategoryByIdQuery(id));
        return productCategory.map(source -> ResponseEntity.ok(ProductCategoryResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}