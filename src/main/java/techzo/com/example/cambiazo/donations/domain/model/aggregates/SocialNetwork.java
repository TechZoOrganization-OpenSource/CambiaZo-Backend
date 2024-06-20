package techzo.com.example.cambiazo.donations.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateSocialNetworkCommand;
import techzo.com.example.cambiazo.donations.domain.model.valueobjects.SocialNetworkName;
import techzo.com.example.cambiazo.donations.domain.model.valueobjects.SocialNetworkUrl;
import techzo.com.example.cambiazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;


@Entity
public class SocialNetwork extends AuditableAbstractAggregateRoot<SocialNetwork> {

    @Embedded
    @Column(nullable = false)
    @NotNull(message = "Name is mandatory")
    private SocialNetworkName name;


    @Embedded
    @Column(nullable = false)
    @NotNull(message = "Description is mandatory")
    private SocialNetworkUrl url;

    @ManyToOne
    @JoinColumn(name = "ong_id")
    @NotNull(message = "Ong is mandatory")
    private Ong ongId;

    public SocialNetwork() {}

    public SocialNetwork(CreateSocialNetworkCommand command, Ong ong) {
        this.name = new SocialNetworkName(command.name());
        this.url = new SocialNetworkUrl(command.url());
        this.ongId = ong;
    }

    public Long getOngId() {
        return ongId.getId();
    }

    public String getName() {
        return name.getSocialNetworkName();
    }

    public String getUrl() {
        return url.getSocialNetworkUrl();
    }

}
