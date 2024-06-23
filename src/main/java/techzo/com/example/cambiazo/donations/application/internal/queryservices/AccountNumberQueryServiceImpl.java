package techzo.com.example.cambiazo.donations.application.internal.queryservices;


import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.AccountNumber;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Ong;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAccountNumberByIdQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAllAccountNumberByOngIdQuery;
import techzo.com.example.cambiazo.donations.domain.services.AccountNumberQueryService;
import techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa.AccountNumberRepository;
import techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa.OngRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccountNumberQueryServiceImpl implements AccountNumberQueryService {

    private final AccountNumberRepository accountNumberRepository;

    private final OngRepository ongRepository;

    public AccountNumberQueryServiceImpl(AccountNumberRepository accountNumberRepository, OngRepository ongRepository) {
        this.accountNumberRepository = accountNumberRepository;
        this.ongRepository = ongRepository;
    }

    @Override
    public Optional<AccountNumber> handle(GetAccountNumberByIdQuery query) {
        return accountNumberRepository.findById(query.id());
    }

    @Override
    public List<AccountNumber> handle(GetAllAccountNumberByOngIdQuery query) {
        Ong ong = ongRepository.findById(query.ongId())
                .orElseThrow(() -> new IllegalArgumentException("Ong with id " + query.ongId() + " not found"));
        return accountNumberRepository.findByOngId(ong);
    }
}
