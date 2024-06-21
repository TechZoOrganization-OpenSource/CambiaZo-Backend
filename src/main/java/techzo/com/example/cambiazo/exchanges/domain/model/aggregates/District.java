package techzo.com.example.cambiazo.exchanges.domain.model.aggregates;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateDistrictCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.valueobjects.DistrictName;

@Setter
@Getter
@Entity
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Column(nullable = false)
    @NotNull(message = "Name is required")
    private DistrictName name;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @NotNull(message = "Department is required")
    private Department departmentId;

    public District() {
    }

    public District(CreateDistrictCommand command, Department department){
        this.name = new DistrictName(command.name());
        this.departmentId = department;
    }

    public Long getDepartmentId() {
        return departmentId.getId();
    }

    public String getName() {
        return name.getDistrictName();
    }

}
