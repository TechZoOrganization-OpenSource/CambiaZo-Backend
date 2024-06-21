package techzo.com.example.cambiazo.donations.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.donations.domain.model.entities.CategoryOng;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAllCategoryOngsQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetCategoryOngByIdQuery;
import techzo.com.example.cambiazo.donations.domain.services.CategoryOngCommandService;
import techzo.com.example.cambiazo.donations.domain.services.CategoryOngQueryService;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.CategoryOngResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.CreateCategoryOngResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.CategoryOngResourceFromEntityAssembler;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.CreateCategoryOngCommandFromResourceAssembler;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/category-ongs")
@Tag(name="CategoryOngs", description="CategoryOngs Management Endpoints")
public class CategoryOngsController {

    private final CategoryOngCommandService categoryOngCommandService;

    private final CategoryOngQueryService categoryOngQueryService;

    public CategoryOngsController(CategoryOngCommandService categoryOngCommandService, CategoryOngQueryService categoryOngQueryService){
        this.categoryOngCommandService=categoryOngCommandService;
        this.categoryOngQueryService=categoryOngQueryService;
    }

    @Operation(summary="Create a new CategoryOng", description="Create a new CategoryOng with the input data.")
    @PostMapping
    public ResponseEntity<CategoryOngResource> createCategoryOng(@RequestBody CreateCategoryOngResource resource){
        Optional<CategoryOng> categoryOng= categoryOngCommandService.handle(CreateCategoryOngCommandFromResourceAssembler.toCommandFromResource(resource));
        return categoryOng.map(source ->new ResponseEntity<>(CategoryOngResourceFromEntityAssembler.toResourceFromEntity(source),CREATED)).orElseGet(()->ResponseEntity.notFound().build());
    }

    @Operation(summary="Get all CategoryOngs", description="Get all CategoryOngs.")
    @GetMapping
    public ResponseEntity<List<CategoryOngResource>> getAllCategoryOngs(){
        var getAllCategoryOngsQuery=new GetAllCategoryOngsQuery();
        var categoryOng = categoryOngQueryService.handle(getAllCategoryOngsQuery);
        var categoryOngResource=categoryOng.stream().map(CategoryOngResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(categoryOngResource);
    }

    @Operation(summary="Get CategoryOng by ID", description="Get CategoryOng by ID.")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryOngResource> getCategoryOngById(@PathVariable Long id){
        Optional<CategoryOng>categoryOng=categoryOngQueryService.handle(new GetCategoryOngByIdQuery(id));
        return categoryOng.map(source->ResponseEntity.ok(CategoryOngResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(()->ResponseEntity.notFound().build());
    }


    @Operation(summary="Delete a CategoryOng", description="Delete a CategoryOng with the input id.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategoryOngById(@PathVariable Long id){
        try {
            categoryOngCommandService.handleDeleteCategoryOng(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
