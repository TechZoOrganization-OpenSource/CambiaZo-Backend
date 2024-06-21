package techzo.com.example.cambiazo.exchanges.domain.model.commands;

public record CreateDistrictCommand(String name, Long departmentId){

    public CreateDistrictCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (departmentId == null) {
            throw new IllegalArgumentException("Department id is required");
        }
    }
}
