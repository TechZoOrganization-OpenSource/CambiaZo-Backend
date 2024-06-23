package techzo.com.example.cambiazo.exchanges.interfaces.rest.resources;

public record CreateMembershipResource(String name, String description, Double price) {

    public CreateMembershipResource {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if (description == null) {
            throw new IllegalArgumentException("description cannot be null");
        }
        if (price == null) {
            throw new IllegalArgumentException("price cannot be null");
        }
    }
}
