package techzo.com.example.cambiazo.donations.interfaces.rest.transform;


import techzo.com.example.cambiazo.donations.domain.model.aggregates.Project;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.ProjectResource;

public class ProjectResourceFromEntityAssembler {
    public static ProjectResource toResourceFromEntity(Project entity) {
        return new ProjectResource(entity.getId(), entity.getName(), entity.getDescription(), entity.getOngId());
    }
}
