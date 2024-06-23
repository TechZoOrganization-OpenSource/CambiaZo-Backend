package techzo.com.example.cambiazo.exchanges.application.internal.commandservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.MembershipNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateUserCommand;
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
