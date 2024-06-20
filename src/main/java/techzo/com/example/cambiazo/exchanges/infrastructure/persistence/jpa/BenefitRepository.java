package techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Benefit;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Membership;

import java.util.List;
import java.util.Optional;

public interface BenefitRepository extends JpaRepository<Benefit, Long>{

    List<Benefit>findAllBenefitsByMembershipId(Membership id);

}
