package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateMembershipCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Membership;

import java.util.Optional;

public interface MembershipCommandService {

    Optional<Membership> handle(CreateMembershipCommand command);

    boolean handleDeleteMembership(Long id);
}
