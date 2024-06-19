package techzo.com.example.cambiazo.donations.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateCategoryOngCommand;


@Setter
@Getter
@Entity
public class CategoryOng{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    public CategoryOng(String name) {
        this.name = name;
    }

    public CategoryOng() {
    }

    public CategoryOng(CreateCategoryOngCommand command) {
        this.name = command.name();
    }

    public Long getCategoryId(){
        return id;
    }

}
