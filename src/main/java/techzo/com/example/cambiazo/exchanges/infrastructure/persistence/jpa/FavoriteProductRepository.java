package techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.FavoriteProduct;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;

import java.util.List;

public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Long> {

    List<FavoriteProduct>findFavoriteProductByUserId(User userId);

}
