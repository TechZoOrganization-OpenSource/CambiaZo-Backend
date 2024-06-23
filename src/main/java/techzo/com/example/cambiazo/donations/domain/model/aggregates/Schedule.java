package techzo.com.example.cambiazo.donations.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateScheduleCommand;
import techzo.com.example.cambiazo.donations.domain.model.valueobjects.ScheduleText;
import techzo.com.example.cambiazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;


@Entity
public class Schedule extends AuditableAbstractAggregateRoot<Schedule> {

    @Column(nullable = false, columnDefinition = "TEXT")
    @Embedded
    @NotNull(message = "Text is mandatory")
    private ScheduleText text;

    @ManyToOne
    @JoinColumn(name = "ong_id")
    @NotNull(message = "Ong is mandatory")
    private Ong ongId;

    public Schedule() {}

    public Schedule(CreateScheduleCommand command, Ong ong) {
        this.text = new ScheduleText(command.text());
        this.ongId = ong;
    }

    public Long getOngId() {
        return ongId.getId();
    }

    public String getText() {
        return text.getScheduleText();
    }
}
