package techzo.com.example.cambiazo.exchanges.interfaces.rest.resources;

public record MembershipResource(Long id, String name, String description, Double price) {

    public MembershipResource {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
    }
}
