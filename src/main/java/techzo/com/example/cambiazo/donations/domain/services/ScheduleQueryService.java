package techzo.com.example.cambiazo.donations.domain.services;

import techzo.com.example.cambiazo.donations.domain.model.aggregates.Schedule;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAllSchedulesByOngIdQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetScheduleByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ScheduleQueryService {

    Optional<Schedule> handle(GetScheduleByIdQuery query);

    List<Schedule>handle(GetAllSchedulesByOngIdQuery query);
}
