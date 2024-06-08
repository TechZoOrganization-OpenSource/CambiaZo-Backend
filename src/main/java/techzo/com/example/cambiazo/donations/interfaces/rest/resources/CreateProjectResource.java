package techzo.com.example.cambiazo.donations.interfaces.rest.resources;

public record CreateProjectResource(String name, String description) {
    public CreateProjectResource {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if (description == null) {
            throw new IllegalArgumentException("description cannot be null");
        }
    }
}
