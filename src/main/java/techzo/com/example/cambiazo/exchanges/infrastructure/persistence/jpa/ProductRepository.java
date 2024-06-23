package techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Product;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByBoost(boolean boost);

    List<Product> findAllByUserIdAndAvailable(User userId, boolean available);

    List<Product> findAllByUserId(User user);

    List<Product> findAllProductsByName(String name);
}
