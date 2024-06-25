package techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.ProductCategory;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    List<ProductCategory> findAll();

    boolean existsByNameAndIdIsNot(String name, Long id);
    boolean existsByName(String name);
}
