package techzo.com.example.cambiazo.exchanges.application.internal.commandservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.MembershipNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.UserNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateUserCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.UpdateProductCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.UpdateUserCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.UpdateUserMembershipCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Membership;
import techzo.com.example.cambiazo.exchanges.domain.services.UserCommandService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.MembershipRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.UserRepository;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;

    private final MembershipRepository membershipRepository;

    public UserCommandServiceImpl(UserRepository userRepository, MembershipRepository membershipRepository) {
        this.userRepository = userRepository;
        this.membershipRepository = membershipRepository;
    }


    @Override
    public Optional<User>handle (CreateUserCommand command) {
        Membership membership = membershipRepository.findById(command.membershipId())
                .orElseThrow(() -> new MembershipNotFoundException(command.membershipId()));

        if (userRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("User with same email already exists");
        }
        var user = new User(command, membership);
        var createdUser = userRepository.save(user);
        return Optional.of(createdUser);
    }

    @Override
    public Optional<User> handle(UpdateUserCommand command) {
        var result = userRepository.findById(command.id());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("User does not exist");
        }

        var userToUpdate = result.get();
        try {
            Membership membership = membershipRepository.findById(command.membershipId())
                    .orElseThrow(() -> new MembershipNotFoundException(command.membershipId()));
            var updatedUser = userRepository.save(userToUpdate.updateInformation(command.name(), command.email(), command.phone(), command.password(), command.profilePicture(), membership));
            return Optional.of(updatedUser);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating user: " + e.getMessage());

        }
    }

    @Override
    public Optional<User> handle(UpdateUserMembershipCommand command) {
        var result = userRepository.findById(command.id());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("User does not exist");
        }

        var userToUpdate = result.get();
        try {
            Membership membership = membershipRepository.findById(command.membershipId())
                    .orElseThrow(() -> new MembershipNotFoundException(command.membershipId()));
            User user = userRepository.findById(command.id())
                    .orElseThrow(() -> new UserNotFoundException(command.id()));
            var updatedUser = userRepository.save(userToUpdate.updateInformation(user.getName(), user.getEmail(), user.getPhone(), user.getPassword(), user.getProfilePicture(), membership));
            return Optional.of(updatedUser);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating user: " + e.getMessage());

        }
    }


    @Override
    public boolean handleDeleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        } else {
            return false;
        }
    }
}
