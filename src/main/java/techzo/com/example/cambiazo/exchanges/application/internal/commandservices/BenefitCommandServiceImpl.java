package techzo.com.example.cambiazo.exchanges.application.internal.commandservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.MembershipNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Benefit;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateBenefitCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Membership;
import techzo.com.example.cambiazo.exchanges.domain.services.BenefitCommandService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.BenefitRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.MembershipRepository;

import java.util.Optional;

@Service
public class BenefitCommandServiceImpl implements BenefitCommandService {

    private final BenefitRepository benefitRepository;

    private final MembershipRepository membershipRepository;

    public BenefitCommandServiceImpl(BenefitRepository benefitRepository, MembershipRepository membershipRepository) {
        this.benefitRepository = benefitRepository;
        this.membershipRepository = membershipRepository;
    }

    @Override
    public Optional<Benefit> handle(CreateBenefitCommand command) {
        Membership membership = membershipRepository.findById(command.membershipId())
                .orElseThrow(() -> new MembershipNotFoundException(command.membershipId()));
        var benefit = new Benefit(command, membership);
        benefitRepository.save(benefit);
        return Optional.of(benefit);
    }

    @Override
    public boolean handleDeleteBenefit(Long id) {
        Optional<Benefit> benefit = benefitRepository.findById(id);
        if (benefit.isPresent()) {
            benefitRepository.delete(benefit.get());
            return true;
        } else {
            return false;
        }
    }
}
