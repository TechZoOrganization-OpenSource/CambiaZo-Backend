package techzo.com.example.cambiazo.donations.application.internal.commandservices;


import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.donations.domain.exceptions.OngNotFoundException;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Ong;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Project;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateProjectCommand;
import techzo.com.example.cambiazo.donations.domain.services.ProjectCommandService;
import techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa.OngRepository;
import techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa.ProjectRepository;

import java.util.Optional;

@Service
public class ProjectCommandServiceImpl implements ProjectCommandService{
    private final ProjectRepository projectRepository;

    private final OngRepository ongRepository;

    public ProjectCommandServiceImpl(ProjectRepository projectRepository, OngRepository ongRepository) {
        this.projectRepository = projectRepository;
        this.ongRepository = ongRepository;
    }

    @Override
    public Optional<Project> handle(CreateProjectCommand command) {
        Ong ong = ongRepository.findById(command.ongId())
                .orElseThrow(() -> new OngNotFoundException(command.ongId()));

        var name = command.name();
        projectRepository.findByName(name).ifPresent(project ->{
            throw new IllegalArgumentException("Project with name already exists");
        });
        var project = new Project(command, ong);
        projectRepository.save(project);
        return Optional.of(project);
    }

    @Override
    public boolean handleDeleteProject(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            projectRepository.delete(project.get());
            return true;
        } else {
            return false;
        }
    }
}
