package techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Membership;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    boolean existsByName(String name);

}
