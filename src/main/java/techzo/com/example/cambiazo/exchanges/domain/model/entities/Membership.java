package techzo.com.example.cambiazo.exchanges.domain.model.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateMembershipCommand;

@Setter
@Getter
@Entity
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    @NotNull(message = "Name is required")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Description is required")
    private String description;

    @Column(nullable = false)
    @NotNull(message = "Price is required")
    private Double price;


    public Membership() {
    }

    public Membership(CreateMembershipCommand command) {
        this.name = command.name();
        this.description = command.description();
        this.price = command.price();
    }

}
