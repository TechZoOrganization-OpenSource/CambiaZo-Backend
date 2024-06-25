package techzo.com.example.cambiazo.donations.domain.services;

import techzo.com.example.cambiazo.donations.domain.model.aggregates.Ong;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateOngCommand;
import techzo.com.example.cambiazo.donations.domain.model.commands.UpdateOngCommand;

import java.util.Optional;

public interface OngCommandService {
    Optional<Ong>handle(CreateOngCommand command);
    Optional<Ong>handle(UpdateOngCommand command);
    boolean handleDeleteOng(Long id);
}
