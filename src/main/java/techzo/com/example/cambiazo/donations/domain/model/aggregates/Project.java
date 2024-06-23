package techzo.com.example.cambiazo.donations.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateProjectCommand;
import techzo.com.example.cambiazo.donations.domain.model.valueobjects.ProjectName;
import techzo.com.example.cambiazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;


@Entity
public class Project extends AuditableAbstractAggregateRoot<Project> {


    @Embedded
    @Column(nullable = false)
    @NotNull(message = "Name is mandatory")
    private ProjectName name;


    @Column(nullable = false, columnDefinition = "TEXT")
    @NotNull(message = "Description is mandatory")
    @Getter
    private String description;

    @ManyToOne
    @JoinColumn(name = "ong_id")
    @NotNull(message = "Ong is mandatory")
    private Ong ongId;

    public Project() {}

    public Project(CreateProjectCommand command, Ong ong) {
        this.name = new ProjectName(command.name());
        this.description = command.description();
        this.ongId = ong;
    }

    public Long getOngId() {
        return ongId.getId();
    }

    public String getName() {
        return name.getProjectName();
    }

}
