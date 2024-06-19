package techzo.com.example.cambiazo.donations.application.internal.queryservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Ong;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.SocialNetwork;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAllSocialNetworkByOngIdQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAllSocialNetworksQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetSocialNetworkByIdQuery;
import techzo.com.example.cambiazo.donations.domain.services.SocialNetworkQueryService;
import techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa.OngRepository;
import techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa.SocialNetworkRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SocialNetworkQueryServiceImpl implements SocialNetworkQueryService{

    private final SocialNetworkRepository socialNetworkRepository;

    private final OngRepository ongRepository;

    public SocialNetworkQueryServiceImpl(SocialNetworkRepository socialNetworkRepository, OngRepository ongRepository) {
        this.socialNetworkRepository = socialNetworkRepository;
        this.ongRepository = ongRepository;
    }

    @Override
    public List<SocialNetwork> handle(GetAllSocialNetworksQuery query) {
        return socialNetworkRepository.findAll();
    }

    @Override
    public Optional<SocialNetwork> handle(GetSocialNetworkByIdQuery query) {
        return socialNetworkRepository.findById(query.id());
    }

    @Override
    public List<SocialNetwork> handle(GetAllSocialNetworkByOngIdQuery query) {
        Ong ong = ongRepository.findById(query.ongId())
                .orElseThrow(() -> new IllegalArgumentException("Ong with id " + query.ongId() + " not found"));
        return socialNetworkRepository.findByOngId(ong);
    }
}
