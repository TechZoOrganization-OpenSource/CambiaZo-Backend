package techzo.com.example.cambiazo.exchanges.application.internal.queryservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Benefit;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Membership;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllBenefitsByMembershipIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllBenefitsQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetBenefitByIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.services.BenefitQueryService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.BenefitRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.MembershipRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BenefitQueryServiceImpl implements BenefitQueryService {

    private final BenefitRepository benefitRepository;

    private final MembershipRepository membershipRepository;

    public BenefitQueryServiceImpl(BenefitRepository benefitRepository, MembershipRepository membershipRepository) {
        this.benefitRepository = benefitRepository;
        this.membershipRepository = membershipRepository;
    }

    @Override
    public Optional<Benefit>handle(GetBenefitByIdQuery query) {
        return benefitRepository.findById(query.id());
    }

    @Override
    public List<Benefit>handle(GetAllBenefitsQuery query){
        return benefitRepository.findAll();
    }

    @Override
    public List<Benefit>handle(GetAllBenefitsByMembershipIdQuery query){
        Membership membership = membershipRepository.findById(query.membershipId())
                .orElseThrow(() -> new IllegalArgumentException("Membership with id " + query.membershipId() + " not found"));
        return benefitRepository.findAllBenefitsByMembershipId(membership);
    }
}
