package techzo.com.example.cambiazo.exchanges.application.internal.queryservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.MembershipNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Membership;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllUsersByMembershipIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllUsersQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetUserByIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.services.UserQueryService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.MembershipRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    private final MembershipRepository membershipRepository;

    public UserQueryServiceImpl(UserRepository userRepository, MembershipRepository membershipRepository) {
        this.userRepository = userRepository;
        this.membershipRepository = membershipRepository;
    }

    @Override
    public List<User> handle(GetAllUsersQuery query){
        return userRepository.findAll();
    }

    @Override
    public List<User>handle(GetAllUsersByMembershipIdQuery query){
        Membership membership = membershipRepository.findById(query.membershipId())
                .orElseThrow(() -> new MembershipNotFoundException(query.membershipId()));
        return userRepository.findAllUsersByMembershipId(membership);
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query){
        return userRepository.findById(query.id());
    }
}
