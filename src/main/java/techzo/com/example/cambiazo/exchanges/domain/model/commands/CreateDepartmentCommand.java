package techzo.com.example.cambiazo.exchanges.domain.model.commands;

public record CreateDepartmentCommand(String name, Long countryId) {

    public CreateDepartmentCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (countryId == null) {
            throw new IllegalArgumentException("Country id is required");
        }
    }
}
