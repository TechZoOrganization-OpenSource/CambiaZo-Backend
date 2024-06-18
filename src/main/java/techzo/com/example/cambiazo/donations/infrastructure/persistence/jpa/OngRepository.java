package techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Ong;

import java.util.Optional;

@Repository
public interface OngRepository extends JpaRepository<Ong, Long>{
    Optional<Ong>findByName(String name);
    Optional<Ong>findByEmail(String email);
}
