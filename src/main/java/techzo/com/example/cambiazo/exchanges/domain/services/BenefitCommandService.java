package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Benefit;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateBenefitCommand;

import java.util.Optional;

public interface BenefitCommandService {
    Optional<Benefit>handle(CreateBenefitCommand command);

    boolean handleDeleteBenefit(Long id);
}
