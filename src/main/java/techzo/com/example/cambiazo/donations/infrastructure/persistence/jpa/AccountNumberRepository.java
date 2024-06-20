package techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.AccountNumber;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Ong;

import java.util.List;
import java.util.Optional;

public interface AccountNumberRepository extends JpaRepository<AccountNumber, Long>{

    Optional<AccountNumber>findByNameAndCciAndAccount(String name, String cci, String account);

    List<AccountNumber>findByOngId(Ong id);
}
