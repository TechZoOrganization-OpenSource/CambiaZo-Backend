package techzo.com.example.cambiazo.donations.domain.services;

import techzo.com.example.cambiazo.donations.domain.model.aggregates.SocialNetwork;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAllSocialNetworkByOngIdQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAllSocialNetworksQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetSocialNetworkByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SocialNetworkQueryService {
    Optional<SocialNetwork> handle(GetSocialNetworkByIdQuery query);
    List<SocialNetwork> handle(GetAllSocialNetworksQuery query);
    List<SocialNetwork> handle(GetAllSocialNetworkByOngIdQuery query);
}
