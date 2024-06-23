package techzo.com.example.cambiazo.exchanges.domain.model.valueobjects;

public record DistrictName(String name) {

    public DistrictName {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
    }

    public String getDistrictName() {
        return name;
    }
}
