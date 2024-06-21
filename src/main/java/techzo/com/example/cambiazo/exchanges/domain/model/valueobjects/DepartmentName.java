package techzo.com.example.cambiazo.exchanges.domain.model.valueobjects;

public record DepartmentName(String name) {
    public DepartmentName {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
    }

    public String getDepartmentName() {
        return name;
    }
}
