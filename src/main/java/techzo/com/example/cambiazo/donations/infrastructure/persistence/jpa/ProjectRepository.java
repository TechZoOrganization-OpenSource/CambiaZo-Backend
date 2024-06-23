package techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Ong;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Project;
import techzo.com.example.cambiazo.donations.domain.model.valueobjects.ProjectName;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository  extends JpaRepository<Project, Long> {

    Optional<Project>findByName(ProjectName name);

    List<Project>findByOngId(Ong id);
}
