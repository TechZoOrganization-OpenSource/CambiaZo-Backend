package techzo.com.example.cambiazo.exchanges.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateUserCommand;
import techzo.com.example.cambiazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;


@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

    @Column(nullable = false)
    @Getter
    private String name;

    @Column(nullable = false)
    @Getter
    private String email;

    @Column(nullable = false)
    @Getter
    private String phone;

    @Column(nullable = false)
    @Getter
    private String password;

    @Column(nullable = false)
    @Getter
    private String profilePicture;

    protected User() {}

    public User(CreateUserCommand command) {
        this.name = command.name();
        this.email = command.email();
        this.phone = command.phone();
        this.password = command.password();
        this.profilePicture = command.profilePicture();
    }
}
