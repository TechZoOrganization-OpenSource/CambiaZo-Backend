package techzo.com.example.cambiazo.exchanges.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateUserCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Membership;
import techzo.com.example.cambiazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.ArrayList;
import java.util.List;


@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

    @Column(nullable = false)
    @NotNull(message = "Name is required")
    @Getter
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Email is required")
    @Getter
    private String email;

    @Column(nullable = false, length = 9)
    @NotNull(message = "Phone is required")
    @Getter
    private String phone;

    @Column(nullable = false)
    @NotNull(message = "Password is required")
    @Getter
    private String password;

    @Column(nullable = false)
    @Getter
    private String profilePicture;

    @ManyToOne
    @JoinColumn(name = "membership_id", nullable = false)
    @NotNull(message = "Membership ID is mandatory")
    private Membership membershipId;

    protected User() {}

    public User(CreateUserCommand command, Membership membership) {
        this.name = command.name();
        this.email = command.email();
        this.phone = command.phone();
        this.password = command.password();
        this.profilePicture = command.profilePicture();
        this.membershipId = membership;
    }

    public Long getMembershipId() {
        return membershipId.getId();
    }
}
