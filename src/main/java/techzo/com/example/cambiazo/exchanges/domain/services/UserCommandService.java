package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateUserCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.UpdateProductCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.UpdateUserCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.UpdateUserMembershipCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(CreateUserCommand command);

    Optional<User> handle(UpdateUserCommand command);

    Optional<User>handle(UpdateUserMembershipCommand command);

    boolean handleDeleteUser(Long id);
}
