package techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Ong;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.SocialNetwork;
import techzo.com.example.cambiazo.donations.domain.model.valueobjects.SocialNetworkName;
import techzo.com.example.cambiazo.donations.domain.model.valueobjects.SocialNetworkUrl;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, Long>{

    Optional<SocialNetwork>findByNameAndUrl(SocialNetworkName name, SocialNetworkUrl url);

    List<SocialNetwork> findByOngId(Ong id);
}
