package techzo.com.example.cambiazo.exchanges.domain.model.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateCountryCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.valueobjects.CountryName;

@Setter
@Getter
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Column(nullable = false)
    @NotNull(message = "name is required")
    private CountryName name;

    public Country() {
    }

    public Country(CreateCountryCommand command) {
        this.name = new CountryName(command.name());
    }

    public String getName() {
        return name.getCountryName();
    }
}
