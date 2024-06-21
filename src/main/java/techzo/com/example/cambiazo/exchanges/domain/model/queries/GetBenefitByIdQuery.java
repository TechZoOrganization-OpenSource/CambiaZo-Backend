package techzo.com.example.cambiazo.exchanges.domain.model.queries;

public record GetBenefitByIdQuery(Long id) {

    public GetBenefitByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("id is required");
        }
    }
}
