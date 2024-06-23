package techzo.com.example.cambiazo.exchanges.domain.model.commands;

public record CreateCountryCommand(String name) {

    public CreateCountryCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
    }

}
