package techzo.com.example.cambiazo.exchanges.domain.model.commands;

public record CreateBenefitCommand(String description, Long membershipId) {

    public CreateBenefitCommand{
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (membershipId == null) {
            throw new IllegalArgumentException("PlanId cannot be null");
        }
    }
}
