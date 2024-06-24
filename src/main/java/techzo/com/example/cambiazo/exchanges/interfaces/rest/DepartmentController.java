package techzo.com.example.cambiazo.exchanges.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllDepartmentsByCountryIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllDepartmentsQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetDepartmentByIdQuery;
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

@RestController
@RequestMapping("/api/v1/departments")
@Tag(name="Departments", description="Departments Management Endpoints")
public class DepartmentController {

    private final DepartmentCommandService departmentCommandService;

    private final DepartmentQueryService departmentQueryService;

    public DepartmentController(DepartmentCommandService departmentCommandService, DepartmentQueryService departmentQueryService){
        this.departmentCommandService=departmentCommandService;
        this.departmentQueryService=departmentQueryService;
    }

    @Operation(summary="Create a new Department", description="Create a new Department with the input data.")
    @PostMapping
    public ResponseEntity<DepartmentResource>createDepartment(@RequestBody CreateDepartmentResource resource){
        try {
            var createDepartmentCommand= CreateDepartmentCommandFromResourceAssembler.toCommandFromResource(resource);
            var department=departmentCommandService.handle(createDepartmentCommand);
            var departmentResource= DepartmentResourceFromEntityAssembler.toResourceFromEntity(department.get());
            return ResponseEntity.status(CREATED).body(departmentResource);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping
    @Operation(summary="Get all Departments", description="Get all Departments.")
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


    @GetMapping("/country/{id}")
    @Operation(summary="Get all Departments by Country ID", description="Get all Departments by Country ID.")
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

    @GetMapping("/{id}")
    @Operation(summary="Get a Department by ID", description="Get a Department with the input id.")
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
