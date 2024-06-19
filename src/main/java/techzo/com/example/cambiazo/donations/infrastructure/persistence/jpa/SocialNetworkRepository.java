package techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.SocialNetwork;

import java.util.Optional;

@Repository
public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, Long>{

    Optional<SocialNetwork>findByNameAndUrl(String name, String url);


}
