package techzo.com.example.cambiazo.exchanges.interfaces.rest.resources;

public record CreateBenefitResource(String description, Long membershipId) {

    public CreateBenefitResource {
        if (description == null) {
            throw new IllegalArgumentException("description cannot be null");
        }
        if (membershipId == null) {
            throw new IllegalArgumentException("membershipId cannot be null");
        }
    }
}
