package techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Project;

import java.util.List;

public interface ProjectRepository  extends JpaRepository<Project, Long> {
    List<Project>findAll();
}
