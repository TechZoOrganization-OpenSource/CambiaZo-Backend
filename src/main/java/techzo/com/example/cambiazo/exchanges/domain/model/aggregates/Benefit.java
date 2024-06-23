package techzo.com.example.cambiazo.exchanges.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateBenefitCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Membership;
import techzo.com.example.cambiazo.exchanges.domain.model.valueobjects.BenefitDescription;

@Setter
@Getter
@Entity
public class Benefit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Embedded
    @Column(nullable = false)
    @NotNull(message = "Name is mandatory")
    private BenefitDescription description;

    @ManyToOne
    @JoinColumn(name = "membership_id", nullable = false)
    @NotNull(message = "Membership ID is mandatory")
    private Membership membershipId;

    public Benefit() {
    }

    public Benefit(CreateBenefitCommand command, Membership membership) {
        this.description = new BenefitDescription(command.description());
        this.membershipId = membership;
    }

    public Long getMembershipId() {
        return membershipId.getId();
    }

    public String getDescription() {
        return description.getBenefitDescription();
    }
}
