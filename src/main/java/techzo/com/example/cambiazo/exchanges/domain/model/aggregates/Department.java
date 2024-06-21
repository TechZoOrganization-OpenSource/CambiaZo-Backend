package techzo.com.example.cambiazo.exchanges.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateDepartmentCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Country;
import techzo.com.example.cambiazo.exchanges.domain.model.valueobjects.DepartmentName;
import techzo.com.example.cambiazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;


@Setter
@Getter
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Column(nullable = false)
    @NotNull(message = "Name is required")
    private DepartmentName name;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    @NotNull(message = "Country is required")
    private Country countryId;


    public Department() {
    }

    public Department(CreateDepartmentCommand command, Country country) {
        this.name = new DepartmentName(command.name());
        this.countryId = country;
    }

    public Long getCountryId() {
        return countryId.getId();
    }

    public String getName() {
        return name.getDepartmentName();
    }

}
