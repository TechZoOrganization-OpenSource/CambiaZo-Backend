package techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Exchange;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Product;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;

import java.util.List;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {


    List<Exchange>findAllExchangesByProductOwnId(Product productOwnId);

    List<Exchange>findAllExchangesByProductChangeId(Product productChangeId);

    List<Exchange> findAllByProductOwnId_UserId(User userId);

    List<Exchange> findAllByProductChangeId_UserId(User userId);

}
