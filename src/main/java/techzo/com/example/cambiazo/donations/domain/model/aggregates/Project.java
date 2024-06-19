package techzo.com.example.cambiazo.donations.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateProjectCommand;
import techzo.com.example.cambiazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;


@Entity
public class Project extends AuditableAbstractAggregateRoot<Project> {

    @Column(nullable = false)
    @NotNull(message = "Name is mandatory")
    @Getter
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Description is mandatory")
    @Getter
    private String description;

    @ManyToOne
    @JoinColumn(name = "ong_id")
    @NotNull(message = "Ong is mandatory")
    private Ong ongId;

    public Project() {}

    public Project(CreateProjectCommand command, Ong ong) {
        this.name = command.name();
        this.description = command.description();
        this.ongId = ong;
    }

    public Long getOngId() {
        return ongId.getId();
    }
}
