package techzo.com.example.cambiazo.exchanges.domain.model.aggregates;


import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateProductCategoryCommand;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ProductCategory extends AbstractAggregateRoot<ProductCategory> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String name;

    protected ProductCategory() {}

    public ProductCategory(CreateProductCategoryCommand command) {
        this.name = command.name();
    }
}