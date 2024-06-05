package techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User>findAll();

    boolean existsByEmail(String email);

}
