package techzo.com.example.cambiazo.exchanges.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.*;
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

/**
 * Controller class for managing District operations through REST endpoints.
 *
 * @author CambiaZo - TechZo
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/districts")
@Tag(name="Districts", description="Districts Management Endpoints")
public class DistrictController {

    private final DistrictCommandService districtCommandService;
    private final DistrictQueryService districtQueryService;

    /**
     * Constructor for DistrictController.
     *
     * @param districtCommandService Service handling commands related to Districts.
     * @param districtQueryService Service handling queries related to Districts.
     */
    public DistrictController(DistrictCommandService districtCommandService, DistrictQueryService districtQueryService){
        this.districtCommandService = districtCommandService;
        this.districtQueryService = districtQueryService;
    }

    /**
     * Endpoint for creating a new District.
     *
     * @param resource The resource containing data to create the District.
     * @return ResponseEntity containing the created DistrictResource.
     */
    @PostMapping
    public ResponseEntity<DistrictResource> createDistrict(@RequestBody CreateDistrictResource resource){
        try {
            var createDistrictCommand = CreateDistrictCommandFromResourceAssembler.toCommandFromResource(resource);
            var district = districtCommandService.handle(createDistrictCommand);
            var districtResource = DistrictResourceFromEntityAssembler.toResourceFromEntity(district.get());
            return ResponseEntity.status(CREATED).body(districtResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint for retrieving all Districts.
     *
     * @return ResponseEntity containing a list of DistrictResources.
     */
    @Operation(summary="Get all Districts", description="Get all Districts.")
    @GetMapping
    public ResponseEntity<List<DistrictResource>> getAllDistricts(){
        try {
            var getAllDistrictsQuery = new GetAllDistrictsQuery();
            var districts = districtQueryService.handle(getAllDistrictsQuery);
            var districtResources = districts.stream().map(DistrictResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(districtResources);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint for retrieving all Districts belonging to a specific department.
     *
     * @param id The ID of the department.
     * @return ResponseEntity containing a list of DistrictResources.
     */
    @GetMapping("/departments/{id}")
    public ResponseEntity<List<DistrictResource>> getDistrictsByDepartmentId(@PathVariable Long id){
        try {
            var getDistrictsByDepartmentIdQuery = new GetAllDistrictsByDepartmentIdQuery(id);
            var districts = districtQueryService.handle(getDistrictsByDepartmentIdQuery);
            var districtResources = districts.stream().map(DistrictResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(districtResources);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint for retrieving a District by its ID.
     *
     * @param id The ID of the District to retrieve.
     * @return ResponseEntity containing the retrieved DistrictResource.
     */
    @Operation(summary="Get a District by ID", description="Get a District by its id.")
    @GetMapping("/{id}")
    public ResponseEntity<DistrictResource> getDistrictById(@PathVariable Long id){
        try {
            var getDistrictByIdQuery = new GetDistrictByIdQuery(id);
            var district = districtQueryService.handle(getDistrictByIdQuery);
            var districtResource = DistrictResourceFromEntityAssembler.toResourceFromEntity(district.get());
            return ResponseEntity.ok(districtResource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }


    /**
     * Endpoint for retrieving a District by its name.
     *
     * @param name The name of the District to retrieve.
     * @return ResponseEntity containing the retrieved DistrictResource.
     */

    @Operation(summary="Get a District by Name", description="Get a District by its name.")
    @GetMapping("/name/{name}")
    public ResponseEntity<DistrictResource> getDistrictByName(@PathVariable String name){
        try {
            var getDistrictByNameQuery = new GetDistrictByNameQuery(name);
            var district = districtQueryService.handle(getDistrictByNameQuery);
            var districtResource = DistrictResourceFromEntityAssembler.toResourceFromEntity(district.get());
            return ResponseEntity.ok(districtResource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint for deleting a District by its ID.
     *
     * @param id The ID of the District to delete.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @Operation(summary="Delete a District", description="Delete a District by its id.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDistrict(@PathVariable Long id){
        try {
            districtCommandService.handleDeleteDistrict(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
