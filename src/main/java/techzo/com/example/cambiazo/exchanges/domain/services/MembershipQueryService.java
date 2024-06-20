package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.entities.Membership;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllMembershipsQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetMembershipByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MembershipQueryService {

    Optional<Membership> handle(GetMembershipByIdQuery query);

    List<Membership>handle(GetAllMembershipsQuery query);
}
