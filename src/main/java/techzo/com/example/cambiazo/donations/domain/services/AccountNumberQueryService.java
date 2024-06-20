package techzo.com.example.cambiazo.donations.domain.services;

import techzo.com.example.cambiazo.donations.domain.model.aggregates.AccountNumber;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAccountNumberByIdQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAllAccountNumberByOngIdQuery;

import java.util.List;
import java.util.Optional;

public interface AccountNumberQueryService {
    Optional<AccountNumber>handle(GetAccountNumberByIdQuery query);
    List<AccountNumber>handle(GetAllAccountNumberByOngIdQuery query);
}
