package techzo.com.example.cambiazo.donations.application.internal.commandservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.donations.domain.exceptions.OngNotFoundException;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.AccountNumber;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Ong;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateAccountNumberCommand;
import techzo.com.example.cambiazo.donations.domain.services.AccountNumberCommandService;
import techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa.AccountNumberRepository;
import techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa.OngRepository;

import java.util.Optional;

@Service
public class AccountNumberCommandServiceImpl implements AccountNumberCommandService {

    private final AccountNumberRepository accountNumberRepository;

    private final OngRepository ongRepository;

    public AccountNumberCommandServiceImpl(AccountNumberRepository accountNumberRepository, OngRepository ongRepository) {
        this.accountNumberRepository = accountNumberRepository;
        this.ongRepository = ongRepository;
    }

    @Override
    public Optional<AccountNumber>handle(CreateAccountNumberCommand command) {
        Ong ong = ongRepository.findById(command.ongId())
                .orElseThrow(() -> new OngNotFoundException(command.ongId()));

        var name = command.name();
        var cci = command.cci();
        var account = command.account();
        accountNumberRepository.findByNameAndCciAndAccount(name, cci, account).ifPresent( accountNumber ->{
            throw new IllegalArgumentException("Account Number with name, cci and account already exists");
        });
        var accountNumber = new AccountNumber(command, ong);
        accountNumberRepository.save(accountNumber);
        return Optional.of(accountNumber);
    }

    @Override
    public boolean handleDeleteAccountNumber(Long id) {
        Optional<AccountNumber> accountNumber = accountNumberRepository.findById(id);
        if (accountNumber.isPresent()) {
            accountNumberRepository.delete(accountNumber.get());
            return true;
        } else {
            return false;
        }
    }
}
