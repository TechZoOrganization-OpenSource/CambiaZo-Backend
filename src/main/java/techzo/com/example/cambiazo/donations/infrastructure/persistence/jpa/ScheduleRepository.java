package techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Ong;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Schedule;
import techzo.com.example.cambiazo.donations.domain.model.valueobjects.ScheduleText;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{

    Optional<Schedule>findByText(ScheduleText text);
    List<Schedule>findByOngId(Ong id);
}
