package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.District;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateDistrictCommand;

import java.util.Optional;

public interface DistrictCommandService {

    Optional<District>handle(CreateDistrictCommand command);

    boolean handleDeleteDistrict(Long id);
}
