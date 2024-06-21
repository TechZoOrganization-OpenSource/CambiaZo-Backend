package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateCountryCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Country;

import java.util.Optional;

public interface CountryCommandService {

    Optional<Country>handle(CreateCountryCommand command);

    boolean handleDeleteCountry(Long id);
}
