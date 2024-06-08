package techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.CategoryOng;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;

import java.util.List;

public interface CategoryOngRepository extends JpaRepository<CategoryOng, Long>{
    List<CategoryOng>findAll();

    boolean existsByName(String name);
}
