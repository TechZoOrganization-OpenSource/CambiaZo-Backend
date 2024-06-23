package techzo.com.example.cambiazo.exchanges.application.internal.queryservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Country;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllCountriesQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetCountryByIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.services.CountryQueryService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.CountryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CountryQueryServiceImpl implements CountryQueryService {

    private final CountryRepository countryRepository;

    public CountryQueryServiceImpl(CountryRepository countryRepository){
        this.countryRepository=countryRepository;
    }

    @Override
    public List<Country>handle(GetAllCountriesQuery query){
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country>handle(GetCountryByIdQuery query){
        return countryRepository.findById(query.id());
    }
}
