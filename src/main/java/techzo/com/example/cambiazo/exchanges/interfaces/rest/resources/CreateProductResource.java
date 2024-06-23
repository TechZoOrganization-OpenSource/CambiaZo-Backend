package techzo.com.example.cambiazo.exchanges.interfaces.rest.resources;

public record CreateProductResource(String name, String description, String desiredObject, Double price, String image, Boolean boost, Boolean available, Long productCategoryId, Long userId,  Long districtId) {

    public CreateProductResource {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if (description == null) {
            throw new IllegalArgumentException("description cannot be null");
        }
        if (desiredObject == null) {
            throw new IllegalArgumentException("desiredObject cannot be null");
        }
        if (price == null) {
            throw new IllegalArgumentException("price cannot be null");
        }
        if (image == null) {
            throw new IllegalArgumentException("image cannot be null");
        }
        if (boost == null) {
            throw new IllegalArgumentException("boost cannot be null");
        }
        if (available == null) {
            throw new IllegalArgumentException("available cannot be null");
        }
        if (productCategoryId == null) {
            throw new IllegalArgumentException("productCategoryId cannot be null");
        }
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }
        if (districtId == null) {
            throw new IllegalArgumentException("districtId cannot be null");
        }
    }
}
