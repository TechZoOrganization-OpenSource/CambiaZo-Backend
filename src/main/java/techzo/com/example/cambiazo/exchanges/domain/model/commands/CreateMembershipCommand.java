package techzo.com.example.cambiazo.exchanges.domain.model.commands;

public record CreateMembershipCommand(String name, String description, Double price) {

    public CreateMembershipCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }
        if (price == null) {
            throw new IllegalArgumentException("Price is required");
        }
    }
}
