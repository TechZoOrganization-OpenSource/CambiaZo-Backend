package techzo.com.example.cambiazo.donations.interfaces.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAllOngsQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetOngByIdQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetOngByLettersQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetOngsByCategoryOngIdQuery;
import techzo.com.example.cambiazo.donations.domain.services.OngCommandService;
import techzo.com.example.cambiazo.donations.domain.services.OngQueryService;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.CreateOngResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.OngResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.UpdateOngResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.CreateOngCommandFromResourceAssembler;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.OngResourceFromEntityAssembler;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.UpdateCategoryOngCommandFromResourceAssembler;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.UpdateOngCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/ongs", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Ongs", description = "Ongs Management Endpoints")
public class OngsController {

    private final OngCommandService ongCommandService;

    private final OngQueryService ongQueryService;

    public OngsController(OngCommandService ongCommandService, OngQueryService ongQueryService) {
        this.ongCommandService = ongCommandService;
        this.ongQueryService = ongQueryService;
    }

    // Endpoint to create a new ong
    @Operation(summary = "Create a new Ong", description = "Create a new Ong with the input data.")
    @PostMapping
    public ResponseEntity<OngResource>createOng(@RequestBody CreateOngResource resource){
        try{
            var createOngCommand = CreateOngCommandFromResourceAssembler.toCommandFromResource(resource);
            var ong = ongCommandService.handle(createOngCommand);
            var ongResource = OngResourceFromEntityAssembler.toResourceFromEntity(ong.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(ongResource); //201
        }catch (IllegalArgumentException e){ //404
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }

    // Endpoint to get all ongs
    @Operation(summary = "Get all Ongs", description = "Get all Ongs.")
    @GetMapping
    public ResponseEntity<List<OngResource>> getAllOngs(){
        try{
            var getAllOngsQuery = new GetAllOngsQuery();
            var ongs = ongQueryService.handle(getAllOngsQuery);
            var ongResources = ongs.stream().map(OngResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(ongResources); //200
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }

    // Endpoint to get ong by id
    @Operation(summary = "Get Ong by ID", description = "Get Ong by ID.")
    @GetMapping("/{id}")
    public ResponseEntity<OngResource> getOngById(@PathVariable Long id){
        try{
            var getOngByIdQuery = new GetOngByIdQuery(id);
            var ong = ongQueryService.handle(getOngByIdQuery);
            var ongResource = OngResourceFromEntityAssembler.toResourceFromEntity(ong.get());
            return ResponseEntity.ok(ongResource); //200
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }

    // Endpoint to get all ongs by categoryOngId
    @Operation(summary = "Get Ongs by Category", description = "Get Ongs by Category.")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<OngResource>> getOngsByCategory(@PathVariable Long categoryId){
        try{
            var getOngsByCategoryQuery = new GetOngsByCategoryOngIdQuery(categoryId);
            var ongs = ongQueryService.handle(getOngsByCategoryQuery);
            var ongResources = ongs.stream().map(OngResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(ongResources); //200
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }

    @Operation(summary = "Get Ongs by Letters", description = "Get Ongs by Letters.")
    @GetMapping("/search/{letters}")
    public ResponseEntity<List<OngResource>> getOngsByLetters(@PathVariable String letters){
        try{
            var getOngByLettersQuery = new GetOngByLettersQuery(letters);
            var ongs = ongQueryService.handle(getOngByLettersQuery);
            var ongResources = ongs.stream().map(OngResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(ongResources); //200
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }

    @Operation(summary = "Update Ong", description = "Update Ong with the input data.")
    @PutMapping("/edit/{ongId}")
    public ResponseEntity<OngResource> updateOng(@PathVariable Long ongId, @RequestBody UpdateOngResource updateOngResource){
        var updateOngCommand = UpdateOngCommandFromResourceAssembler.toCommandFromResource(ongId, updateOngResource);
        var updatedOng = ongCommandService.handle(updateOngCommand);
        if (updatedOng.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var ongResource = OngResourceFromEntityAssembler.toResourceFromEntity(updatedOng.get());
        return ResponseEntity.ok(ongResource);
    }


    // Endpoint to delete ong by id
    @Operation(summary = "Delete Ong by ID", description = "Delete Ong by ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOng(@PathVariable Long id){
        try{
            ongCommandService.handleDeleteOng(id);
            return ResponseEntity.noContent().build(); //204
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }

}
