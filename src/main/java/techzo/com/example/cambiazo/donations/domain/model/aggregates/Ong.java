package techzo.com.example.cambiazo.donations.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateOngCommand;
import techzo.com.example.cambiazo.donations.domain.model.entities.CategoryOng;
import techzo.com.example.cambiazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
public class Ong extends AuditableAbstractAggregateRoot<Ong> {


    @Column(nullable = false)
    @NotNull(message = "Name is mandatory")
    @Getter
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Type is mandatory")
    @Getter
    private String type;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotNull(message = "AboutUs is mandatory")
    @Getter
    private String aboutUs;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotNull(message = "Mission And Vision is mandatory")
    @Getter
    private String missionAndVision;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotNull(message = "supportForm is mandatory")
    @Getter
    private String supportForm;

    @Column(nullable = false)
    @NotNull(message = "Address is mandatory")
    @Getter
    private String address;

    @Column(nullable = false)
    @NotNull(message = "Email is mandatory")
    @Getter
    private String email;

    @Column(nullable = false)
    @NotNull(message = "Phone is mandatory")
    @Getter
    private String phone;

    @Column(nullable = false)
    @NotNull(message = "Logo is mandatory")
    @Getter
    private String logo;

    @Column(nullable = false)
    @NotNull(message = "Website is mandatory")
    @Getter
    private String website;

    @ManyToOne
    @JoinColumn(name = "category_ong_id")
    @NotNull(message = "CategoryOngId is mandatory")
    private CategoryOng categoryOngId;

    public Ong(CreateOngCommand command, CategoryOng categoryOng) {
        this.categoryOngId = categoryOng;
        this.name = command.name();
        this.type = command.type();
        this.aboutUs = command.aboutUs();
        this.missionAndVision = command.missionAndVision();
        this.supportForm = command.supportForm();
        this.address = command.address();
        this.email = command.email();
        this.phone = command.phone();
        this.logo = command.logo();
        this.website = command.website();
    }

    public Ong(){}

    public Long getCategoryOngId() {
        return categoryOngId.getId();
    }
}
