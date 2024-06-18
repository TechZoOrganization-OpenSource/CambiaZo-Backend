package techzo.com.example.cambiazo.donations.interfaces.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Project;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAllProjectsQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetProjectByIdQuery;
import techzo.com.example.cambiazo.donations.domain.services.ProjectCommandService;
import techzo.com.example.cambiazo.donations.domain.services.ProjectQueryService;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.CreateProjectResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.ProjectResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.CreateProjectCommandFromResourceAssembler;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.ProjectResourceFromEntityAssembler;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    private final ProjectCommandService projectCommandService;

    private final ProjectQueryService projectQueryService;

    public ProjectController(ProjectCommandService projectCommandService, ProjectQueryService projectQueryService){
        this.projectCommandService=projectCommandService;
        this.projectQueryService=projectQueryService;
    }

    @PostMapping
    public ResponseEntity<ProjectResource> createProject(@RequestBody CreateProjectResource resource){
        Optional<Project> project= projectCommandService.handle(CreateProjectCommandFromResourceAssembler.toCommandFromResource(resource));
        return project.map(source ->new ResponseEntity<>(ProjectResourceFromEntityAssembler.toResourceFromEntity(source),CREATED)).orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProjectResource>> getAllProjects(){
        var getAllProjectsQuery=new GetAllProjectsQuery();
        var project = projectQueryService.handle(getAllProjectsQuery);
        var projectResource=project.stream().map(ProjectResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(projectResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResource> getProjectById(@PathVariable Long id){
        Optional<Project>project=projectQueryService.handle(new GetProjectByIdQuery(id));
        return project.map(source->ResponseEntity.ok(ProjectResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(()->ResponseEntity.notFound().build());
    }
}
