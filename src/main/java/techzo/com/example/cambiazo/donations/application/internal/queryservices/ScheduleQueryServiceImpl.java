package techzo.com.example.cambiazo.donations.application.internal.queryservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Ong;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Schedule;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAllSchedulesByOngIdQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetScheduleByIdQuery;
import techzo.com.example.cambiazo.donations.domain.services.ScheduleQueryService;
import techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa.OngRepository;
import techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa.ScheduleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleQueryServiceImpl implements ScheduleQueryService {

    private final ScheduleRepository scheduleRepository;

    private final OngRepository ongRepository;

    public ScheduleQueryServiceImpl(ScheduleRepository scheduleRepository, OngRepository ongRepository) {
        this.scheduleRepository = scheduleRepository;
        this.ongRepository = ongRepository;
    }


    @Override
    public Optional<Schedule> handle(GetScheduleByIdQuery query) {
        return scheduleRepository.findById(query.id());
    }

    @Override
    public List<Schedule> handle(GetAllSchedulesByOngIdQuery query) {
        Ong ong = ongRepository.findById(query.ongId())
                .orElseThrow(() -> new IllegalArgumentException("Ong with id " + query.ongId() + " not found"));
        return scheduleRepository.findByOngId(ong);
    }
}
