package techzo.com.example.cambiazo.donations.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateCategoryOngCommand;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class CategoryOng extends AbstractAggregateRoot<CategoryOng> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String name;

    protected CategoryOng() {}

    public CategoryOng(CreateCategoryOngCommand command) {
        this.name = command.name();
    }
}
