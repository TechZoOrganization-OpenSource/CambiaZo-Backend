package techzo.com.example.cambiazo.donations.domain.model.commands;

public record CreateProjectCommand(String name, String description) {
    public CreateProjectCommand{
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
    }
}
