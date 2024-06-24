package techzo.com.example.cambiazo.exchanges.interfaces.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllDistrictsByDepartmentIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllDistrictsQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetDistrictByIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.services.DistrictCommandService;
import techzo.com.example.cambiazo.exchanges.domain.services.DistrictQueryService;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateDistrictResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.DistrictResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.CreateDistrictCommandFromResourceAssembler;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.DistrictResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/districts")
@Tag(name="Districts", description="Districts Management Endpoints")
public class DistrictController {
    private final DistrictCommandService districtCommandService;

    private final DistrictQueryService districtQueryService;

    public DistrictController(DistrictCommandService districtCommandService, DistrictQueryService districtQueryService){
        this.districtCommandService=districtCommandService;
        this.districtQueryService=districtQueryService;
    }

    @PostMapping
    public ResponseEntity<DistrictResource>createDistrict(@RequestBody CreateDistrictResource resource){
        try {
            var createDistrictCommand= CreateDistrictCommandFromResourceAssembler.toCommandFromResource(resource);
            var district=districtCommandService.handle(createDistrictCommand);
            var districtResource= DistrictResourceFromEntityAssembler.toResourceFromEntity(district.get());
            return ResponseEntity.status(CREATED).body(districtResource);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }


    @Operation(summary="Get all Districts", description="Get all Districts.")
    @GetMapping
    public ResponseEntity<List<DistrictResource>> getAllDistricts(){
        try {
            var getAllDistricts = new GetAllDistrictsQuery();
            var districts = districtQueryService.handle(getAllDistricts);
            var districtResources = districts.stream().map(DistrictResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(districtResources);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<List<DistrictResource>> getDistrictsByDepartmentId(@PathVariable Long id){
        try {
            var getDistrictsByDepartmentId = new GetAllDistrictsByDepartmentIdQuery(id);
            var districts = districtQueryService.handle(getDistrictsByDepartmentId);
            var districtResources = districts.stream().map(DistrictResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(districtResources);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary="Get a District by ID", description="Get a District by its id.")
    @GetMapping("/{id}")
    public ResponseEntity<DistrictResource> getDistrictById(@PathVariable Long id){
        try {
            var getDistrictById = new GetDistrictByIdQuery(id);
            var district = districtQueryService.handle(getDistrictById);
            var districtResource = DistrictResourceFromEntityAssembler.toResourceFromEntity(district.get());
            return ResponseEntity.ok(districtResource);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary="Delete a District", description="Delete a District by its id.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDistrict(@PathVariable Long id){
        try {
            districtCommandService.handleDeleteDistrict(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }


}
