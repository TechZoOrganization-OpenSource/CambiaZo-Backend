package techzo.com.example.cambiazo.donations.domain.services;

import techzo.com.example.cambiazo.donations.domain.model.aggregates.Schedule;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateScheduleCommand;

import java.util.Optional;

public interface ScheduleCommandService {

    Optional<Schedule>handle(CreateScheduleCommand command);
    boolean handleDeleteSchedule(Long id);
}
