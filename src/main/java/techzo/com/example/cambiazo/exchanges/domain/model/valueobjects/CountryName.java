package techzo.com.example.cambiazo.exchanges.domain.model.valueobjects;

public record CountryName(String name) {

    public CountryName {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
    }

    public String getCountryName() {
        return name;
    }

}
