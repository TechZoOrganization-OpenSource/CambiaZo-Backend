package techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import techzo.com.example.cambiazo.donations.domain.model.entities.CategoryOng;

import java.util.List;

public interface CategoryOngRepository extends JpaRepository<CategoryOng, Long>{
    boolean existsByName(String name);
    boolean existsById(Long Id);

    boolean existsByNameAndIdIsNot(String name, Long id);
}
