package techzo.com.example.cambiazo.donations.interfaces.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Project;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAllProjectsQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetProjectByIdQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetProjectsByOngIdQuery;
import techzo.com.example.cambiazo.donations.domain.services.ProjectCommandService;
import techzo.com.example.cambiazo.donations.domain.services.ProjectQueryService;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.CreateProjectResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.ProjectResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.CreateProjectCommandFromResourceAssembler;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.ProjectResourceFromEntityAssembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/projects")
@Tag(name="Projects", description="Projects Management Endpoints")
public class ProjectController {
    private final ProjectCommandService projectCommandService;

    private final ProjectQueryService projectQueryService;

    public ProjectController(ProjectCommandService projectCommandService, ProjectQueryService projectQueryService){
        this.projectCommandService=projectCommandService;
        this.projectQueryService=projectQueryService;
    }

    @Operation(summary="Create a new Project", description="Create a new Project with the input data.")
    @PostMapping
    public ResponseEntity<ProjectResource>createProject(@RequestBody CreateProjectResource resource){
        try {
            var createProjectCommand= CreateProjectCommandFromResourceAssembler.toCommandFromResource(resource);
            var project=projectCommandService.handle(createProjectCommand);
            var projectResource=ProjectResourceFromEntityAssembler.toResourceFromEntity(project.get());
            return ResponseEntity.status(CREATED).body(projectResource);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

   

}
