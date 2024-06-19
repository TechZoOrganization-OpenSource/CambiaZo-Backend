package techzo.com.example.cambiazo.donations.domain.services;

import techzo.com.example.cambiazo.donations.domain.model.aggregates.SocialNetwork;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateSocialNetworkCommand;

import java.util.Optional;

public interface SocialNetworkCommandService {

    Optional<SocialNetwork> handle(CreateSocialNetworkCommand command);
    boolean handleDeleteSocialNetwork(Long id);
}
