package techzo.com.example.cambiazo.donations.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateScheduleCommand;
import techzo.com.example.cambiazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;


@Entity
public class Schedule extends AuditableAbstractAggregateRoot<Schedule> {

    @Column(nullable = false)
    @NotNull(message = "Text is mandatory")
    @Getter
    private String text;

    @ManyToOne
    @JoinColumn(name = "ong_id")
    @NotNull(message = "Ong is mandatory")
    private Ong ongId;

    public Schedule() {}

    public Schedule(CreateScheduleCommand command, Ong ong) {
        this.text = command.text();
        this.ongId = ong;
    }

    public Long getOngId() {
        return ongId.getId();
    }
}
