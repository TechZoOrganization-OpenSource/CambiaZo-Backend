package techzo.com.example.cambiazo.donations.application.internal.commandservices;


import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.donations.domain.exceptions.CategoryOngNotFoundException;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Ong;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateOngCommand;
import techzo.com.example.cambiazo.donations.domain.model.entities.CategoryOng;
import techzo.com.example.cambiazo.donations.domain.services.OngCommandService;
import techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa.CategoryOngRepository;
import techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa.OngRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class OngCommandServiceImpl implements OngCommandService{

    private final OngRepository ongRepository;

    private final CategoryOngRepository categoryOngRepository;

    public OngCommandServiceImpl(OngRepository ongRepository, CategoryOngRepository categoryOngRepository) {
        this.ongRepository = ongRepository;
        this.categoryOngRepository = categoryOngRepository;
    }

    @Override
    public Optional<Ong> handle(CreateOngCommand command){
        CategoryOng categoryOng = categoryOngRepository.findById(command.categoryOngId())
                .orElseThrow(() -> new CategoryOngNotFoundException(command.categoryOngId()));

        var name = command.name();
        ongRepository.findByName(name).ifPresent(ong ->{
            throw new IllegalArgumentException("Ong with name already exists");
        });

        var email = command.email();
        ongRepository.findByEmail(email).ifPresent(ong ->{
            throw new IllegalArgumentException("Ong with email already exists");
        });

        var ong = new Ong(command, categoryOng);
        ongRepository.save(ong);
        return Optional.of(ong);
    }
}

