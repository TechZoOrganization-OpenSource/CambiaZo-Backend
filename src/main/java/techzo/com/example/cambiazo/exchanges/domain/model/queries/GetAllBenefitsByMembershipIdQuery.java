package techzo.com.example.cambiazo.exchanges.domain.model.queries;

public record GetAllBenefitsByMembershipIdQuery(Long membershipId) {

    public GetAllBenefitsByMembershipIdQuery {
        if (membershipId == null) {
            throw new IllegalArgumentException("MembershipId cannot be null");
        }
    }
}
