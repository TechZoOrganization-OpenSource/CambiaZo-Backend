package techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.AccountNumber;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Ong;
import techzo.com.example.cambiazo.donations.domain.model.valueobjects.AccountNumberAccount;
import techzo.com.example.cambiazo.donations.domain.model.valueobjects.AccountNumberCci;
import techzo.com.example.cambiazo.donations.domain.model.valueobjects.AccountNumberName;

import java.util.List;
import java.util.Optional;

public interface AccountNumberRepository extends JpaRepository<AccountNumber, Long>{

    Optional<AccountNumber>findByNameAndCciAndAccount(AccountNumberName name, AccountNumberCci cci, AccountNumberAccount account);

    List<AccountNumber>findByOngId(Ong id);
}
