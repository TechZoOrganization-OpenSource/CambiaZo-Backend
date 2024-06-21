package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Benefit;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllBenefitsByMembershipIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllBenefitsQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetBenefitByIdQuery;

import java.util.List;
import java.util.Optional;

public interface BenefitQueryService {

    Optional<Benefit>handle(GetBenefitByIdQuery query);

    List<Benefit>handle(GetAllBenefitsQuery query);

    List<Benefit>handle(GetAllBenefitsByMembershipIdQuery query);
}
