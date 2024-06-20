package techzo.com.example.cambiazo.donations.application.internal.commandservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.donations.domain.exceptions.OngNotFoundException;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Ong;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Schedule;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateScheduleCommand;
import techzo.com.example.cambiazo.donations.domain.services.ScheduleCommandService;
import techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa.OngRepository;
import techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa.ScheduleRepository;

import java.util.Optional;

@Service
public class ScheduleCommandServiceImpl implements ScheduleCommandService{

    private final ScheduleRepository scheduleRepository;

    private final OngRepository ongRepository;

    public ScheduleCommandServiceImpl(ScheduleRepository scheduleRepository, OngRepository ongRepository) {
        this.scheduleRepository = scheduleRepository;
        this.ongRepository = ongRepository;
    }

    @Override
    public Optional<Schedule> handle(CreateScheduleCommand command) {
        Ong ong = ongRepository.findById(command.ongId())
                .orElseThrow(() -> new OngNotFoundException(command.ongId()));

        var text = command.text();
        scheduleRepository.findByText(text).ifPresent(schedule -> {
            throw new IllegalArgumentException("Schedule with text already exists");
        });

        var schedule = new Schedule(command, ong);
        scheduleRepository.save(schedule);
        return Optional.of(schedule);
    }

    @Override
    public boolean handleDeleteSchedule(Long id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isPresent()) {
            scheduleRepository.delete(schedule.get());
            return true;
        } else {
            return false;
        }
    }
}
