package techzo.com.example.cambiazo.donations.domain.model.aggregates;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateAccountNumberCommand;
import techzo.com.example.cambiazo.donations.domain.model.valueobjects.AccountNumberAccount;
import techzo.com.example.cambiazo.donations.domain.model.valueobjects.AccountNumberCci;
import techzo.com.example.cambiazo.donations.domain.model.valueobjects.AccountNumberName;
import techzo.com.example.cambiazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
public class AccountNumber extends AuditableAbstractAggregateRoot<AccountNumber>{

    @Embedded
    @Column(nullable = false)
    @NotNull(message = "Name is mandatory")
    private AccountNumberName name;


    @Embedded
    @Column(nullable = false)
    @NotNull(message = "CCI is mandatory")
    private AccountNumberCci cci;

    @Embedded
    @Column(nullable = false)
    @NotNull(message = "Account Number is mandatory")
    private AccountNumberAccount account;

    @ManyToOne
    @JoinColumn(name = "ong_id")
    @NotNull(message = "Ong is mandatory")
    private Ong ongId;

    public AccountNumber() {}

    public AccountNumber(CreateAccountNumberCommand command, Ong ong) {
        this.name = new AccountNumberName(command.name());
        this.cci = new AccountNumberCci(command.cci());
        this.account = new AccountNumberAccount(command.account());
        this.ongId = ong;
    }

    public Long getOngId() {
        return ongId.getId();
    }

    public String getName() {
        return name.getAccountNumberName();
    }

    public String getCci() {
        return cci.getAccountNumberCci();
    }

    public String getAccount() {
        return account.getAccountNumberAccount();
    }

}
