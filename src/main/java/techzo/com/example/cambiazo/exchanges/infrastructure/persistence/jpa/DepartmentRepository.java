package techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Department;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Country;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department>findAllDepartmentsByCountryId(Country id);
}
