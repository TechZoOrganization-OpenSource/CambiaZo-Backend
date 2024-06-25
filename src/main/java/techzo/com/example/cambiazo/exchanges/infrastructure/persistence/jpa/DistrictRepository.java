package techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Department;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.District;
import techzo.com.example.cambiazo.exchanges.domain.model.valueobjects.DistrictName;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District>findAllDistrictsByDepartmentId(Department id);

    Optional<District>findByName(DistrictName name);
}
