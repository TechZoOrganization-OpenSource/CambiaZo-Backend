package techzo.com.example.cambiazo.donations.application.internal.commandservices;


import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Project;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateProjectCommand;
import techzo.com.example.cambiazo.donations.domain.services.ProjectCommandService;
import techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa.ProjectRepository;

import java.util.Optional;

@Service
public class ProjectCommandServiceImpl implements ProjectCommandService{
    private final ProjectRepository projectRepository;

    public ProjectCommandServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<Project> handle(CreateProjectCommand command) {
        var project = new Project(command);
        var createdProject = projectRepository.save(project);
        return Optional.of(createdProject);
    }
}
