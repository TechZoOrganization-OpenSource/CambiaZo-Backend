package techzo.com.example.cambiazo.exchanges.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.*;
import techzo.com.example.cambiazo.exchanges.domain.services.DepartmentCommandService;
import techzo.com.example.cambiazo.exchanges.domain.services.DepartmentQueryService;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.CreateDepartmentResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.resources.DepartmentResource;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.CreateDepartmentCommandFromResourceAssembler;
import techzo.com.example.cambiazo.exchanges.interfaces.rest.transform.DepartmentResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Controller class for managing Department operations through REST endpoints.
 *
 * @author CambiaZo - TechZo
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/departments")
@Tag(name="Departments", description="Departments Management Endpoints")
public class DepartmentController {

    private final DepartmentCommandService departmentCommandService;
    private final DepartmentQueryService departmentQueryService;

    /**
     * Constructor for DepartmentController.
     *
     * @param departmentCommandService Service handling commands related to Departments.
     * @param departmentQueryService Service handling queries related to Departments.
     */
    public DepartmentController(DepartmentCommandService departmentCommandService, DepartmentQueryService departmentQueryService){
        this.departmentCommandService = departmentCommandService;
        this.departmentQueryService = departmentQueryService;
    }

    /**
     * Endpoint for creating a new Department.
     *
     * @param resource The resource containing data to create the Department.
     * @return ResponseEntity containing the created DepartmentResource.
     */
    @Operation(summary="Create a new Department", description="Create a new Department with the input data.")
    @PostMapping
    public ResponseEntity<DepartmentResource> createDepartment(@RequestBody CreateDepartmentResource resource){
        try {
            var createDepartmentCommand = CreateDepartmentCommandFromResourceAssembler.toCommandFromResource(resource);
            var department = departmentCommandService.handle(createDepartmentCommand);
            var departmentResource = DepartmentResourceFromEntityAssembler.toResourceFromEntity(department.get());
            return ResponseEntity.status(CREATED).body(departmentResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint for retrieving all Departments.
     *
     * @return ResponseEntity containing a list of DepartmentResources.
     */
    @Operation(summary="Get all Departments", description="Get all Departments.")
    @GetMapping
    public ResponseEntity<List<DepartmentResource>> getAllDepartments(){
        try {
            var getAllDepartments = new GetAllDepartmentsQuery();
            var departments = departmentQueryService.handle(getAllDepartments);
            var departmentResources = departments.stream().map(DepartmentResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(departmentResources);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint for retrieving all Departments belonging to a specific country.
     *
     * @param id The ID of the country.
     * @return ResponseEntity containing a list of DepartmentResources.
     */
    @Operation(summary="Get all Departments by Country ID", description="Get all Departments by Country ID.")
    @GetMapping("/country/{id}")
    public ResponseEntity<List<DepartmentResource>> getAllDepartmentsByCountry(@PathVariable Long id){
        try {
            var getAllDepartmentsByCountry = new GetAllDepartmentsByCountryIdQuery(id);
            var departments = departmentQueryService.handle(getAllDepartmentsByCountry);
            var departmentResources = departments.stream().map(DepartmentResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(departmentResources);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint for retrieving a Department by its ID.
     *
     * @param id The ID of the Department to retrieve.
     * @return ResponseEntity containing the retrieved DepartmentResource.
     */
    @Operation(summary="Get a Department by ID", description="Get a Department with the input id.")
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResource> getDepartment(@PathVariable Long id){
        try {
            var getDepartmentByIdQuery = new GetDepartmentByIdQuery(id);
            var department = departmentQueryService.handle(getDepartmentByIdQuery);
            var departmentResource = DepartmentResourceFromEntityAssembler.toResourceFromEntity(department.get());
            return ResponseEntity.ok(departmentResource);
        }catch ( IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint for deleting a Department by its ID.
     *
     * @param id The ID of the Department to delete.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @Operation(summary="Delete a Department", description="Delete a Department with the input id.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id){
        try {
            departmentCommandService.handleDeleteDepartment(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
