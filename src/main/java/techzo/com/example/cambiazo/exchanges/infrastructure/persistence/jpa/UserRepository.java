package techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Membership;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User>findAllUsersByMembershipId(Membership id);

    boolean existsByEmail(String email);

}
