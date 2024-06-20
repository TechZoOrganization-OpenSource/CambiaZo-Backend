package techzo.com.example.cambiazo.donations.domain.model.aggregates;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateAccountNumberCommand;
import techzo.com.example.cambiazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
public class AccountNumber extends AuditableAbstractAggregateRoot<AccountNumber>{

    @Column(nullable = false)
    @NotNull(message = "Name is mandatory")
    @Getter
    private String name;


    @Column(nullable = false)
    @NotNull(message = "CCI is mandatory")
    @Getter
    private String cci;

    @Column(nullable = false)
    @NotNull(message = "Account Number is mandatory")
    @Getter
    private String account;

    @ManyToOne
    @JoinColumn(name = "ong_id")
    @NotNull(message = "Ong is mandatory")
    private Ong ongId;

    public AccountNumber() {}

    public AccountNumber(CreateAccountNumberCommand command, Ong ong) {
        this.name = command.name();
        this.cci = command.cci();
        this.account = command.account();
        this.ongId = ong;
    }

    public Long getOngId() {
        return ongId.getId();
    }

}
