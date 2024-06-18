package techzo.com.example.cambiazo.donations.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateProjectCommand;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String name;

    @Column(nullable = false)
    @Getter
    private String description;

    protected Project() {}

    public Project(CreateProjectCommand command) {
        this.name = command.name();
        this.description = command.description();
    }
}
