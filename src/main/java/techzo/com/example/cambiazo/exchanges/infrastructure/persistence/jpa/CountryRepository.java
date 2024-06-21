package techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Country;
import techzo.com.example.cambiazo.exchanges.domain.model.valueobjects.CountryName;

public interface CountryRepository extends JpaRepository<Country, Long> {

    boolean existsByName(CountryName name);
}
