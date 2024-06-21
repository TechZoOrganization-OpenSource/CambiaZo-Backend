package techzo.com.example.cambiazo.exchanges.application.internal.commandservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateCountryCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Country;
import techzo.com.example.cambiazo.exchanges.domain.model.valueobjects.CountryName;
import techzo.com.example.cambiazo.exchanges.domain.services.CountryCommandService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.CountryRepository;

import java.util.Optional;

@Service
public class CountryCommandServiceImpl implements CountryCommandService {

    private final CountryRepository countryRepository;

    public CountryCommandServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country>handle(CreateCountryCommand command) {
        var name = new CountryName(command.name());
        if (countryRepository.existsByName(name)) {
            throw new IllegalArgumentException("Country with same name already exists");
        }
        var country = new Country(command);
        var createdCountry = countryRepository.save(country);
        return Optional.of(createdCountry);
    }

    @Override
    public boolean handleDeleteCountry(Long id) {
        Optional<Country> country = countryRepository.findById(id);
        if (country.isPresent()) {
            countryRepository.delete(country.get());
            return true;
        } else {
            return false;
        }
    }
}
