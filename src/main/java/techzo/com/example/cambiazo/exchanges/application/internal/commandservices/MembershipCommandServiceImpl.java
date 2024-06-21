package techzo.com.example.cambiazo.exchanges.application.internal.commandservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateMembershipCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Membership;
import techzo.com.example.cambiazo.exchanges.domain.services.MembershipCommandService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.MembershipRepository;

import java.util.Optional;

@Service
public class MembershipCommandServiceImpl implements MembershipCommandService {

    private final MembershipRepository membershipRepository;

    public MembershipCommandServiceImpl(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Override
    public Optional<Membership> handle(CreateMembershipCommand command) {
        if (membershipRepository.existsByName(command.name())) {
            throw new IllegalArgumentException("Plan with same name already exists");
        }
        var membership = new Membership(command);
        var createdMembership = membershipRepository.save(membership);
        return Optional.of(createdMembership);
    }

    @Override
    public boolean handleDeleteMembership(Long id) {
        Optional<Membership> membership = membershipRepository.findById(id);
        if (membership.isPresent()) {
            membershipRepository.delete(membership.get());
            return true;
        } else {
            return false;
        }
    }
}
