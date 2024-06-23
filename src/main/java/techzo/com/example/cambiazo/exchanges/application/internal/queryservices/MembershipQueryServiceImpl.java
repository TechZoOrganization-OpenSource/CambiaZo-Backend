package techzo.com.example.cambiazo.exchanges.application.internal.queryservices;


import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Membership;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllMembershipsQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetMembershipByIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.services.MembershipQueryService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.MembershipRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipQueryServiceImpl implements MembershipQueryService {

    private final MembershipRepository membershipRepository;

    public MembershipQueryServiceImpl(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Override
    public List<Membership> handle(GetAllMembershipsQuery query) {
        return membershipRepository.findAll();
    }

    @Override
    public Optional<Membership> handle(GetMembershipByIdQuery query) {
        return membershipRepository.findById(query.id());
    }
}
