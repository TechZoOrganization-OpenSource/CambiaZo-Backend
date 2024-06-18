package techzo.com.example.cambiazo.donations.interfaces.rest;

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

@RestController
@RequestMapping("/api/v1/category-ongs")
public class CategoryOngsController {

    private final CategoryOngCommandService categoryOngCommandService;

    private final CategoryOngQueryService categoryOngQueryService;

    public CategoryOngsController(CategoryOngCommandService categoryOngCommandService, CategoryOngQueryService categoryOngQueryService){
        this.categoryOngCommandService=categoryOngCommandService;
        this.categoryOngQueryService=categoryOngQueryService;
    }

    @PostMapping
    public ResponseEntity<CategoryOngResource> createCategoryOng(@RequestBody CreateCategoryOngResource resource){
        Optional<CategoryOng> categoryOng= categoryOngCommandService.handle(CreateCategoryOngCommandFromResourceAssembler.toCommandFromResource(resource));
        return categoryOng.map(source ->new ResponseEntity<>(CategoryOngResourceFromEntityAssembler.toResourceFromEntity(source),CREATED)).orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CategoryOngResource>> getAllCategoryOngs(){
        var getAllCategoryOngsQuery=new GetAllCategoryOngsQuery();
        var categoryOng = categoryOngQueryService.handle(getAllCategoryOngsQuery);
        var categoryOngResource=categoryOng.stream().map(CategoryOngResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(categoryOngResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryOngResource> getCategoryOngById(@PathVariable Long id){
        Optional<CategoryOng>categoryOng=categoryOngQueryService.handle(new GetCategoryOngByIdQuery(id));
        return categoryOng.map(source->ResponseEntity.ok(CategoryOngResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(()->ResponseEntity.notFound().build());
    }
}
