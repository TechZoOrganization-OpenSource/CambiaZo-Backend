package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.entities.Country;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllCountriesQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetCountryByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CountryQueryService {

    Optional<Country> handle(GetCountryByIdQuery query);

    List<Country>handle(GetAllCountriesQuery query);
}
