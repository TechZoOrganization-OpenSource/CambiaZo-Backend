package techzo.com.example.cambiazo.donations.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateSocialNetworkCommand;
import techzo.com.example.cambiazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;


@Entity
public class SocialNetwork extends AuditableAbstractAggregateRoot<SocialNetwork> {
    @Column(nullable = false)
    @NotNull(message = "Name is mandatory")
    @Getter
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Description is mandatory")
    @Getter
    private String url;

    @ManyToOne
    @JoinColumn(name = "ong_id")
    @NotNull(message = "Ong is mandatory")
    private Ong ongId;

    public SocialNetwork() {}

    public SocialNetwork(CreateSocialNetworkCommand command, Ong ong) {
        this.name = command.name();
        this.url = command.url();
        this.ongId = ong;
    }

    public Long getOngId() {
        return ongId.getId();
    }

}
