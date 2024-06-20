package techzo.com.example.cambiazo.donations.domain.services;

import techzo.com.example.cambiazo.donations.domain.model.aggregates.AccountNumber;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateAccountNumberCommand;

import java.util.Optional;

public interface AccountNumberCommandService {

    Optional<AccountNumber>handle(CreateAccountNumberCommand command);
    boolean handleDeleteAccountNumber(Long id);
}
