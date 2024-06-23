package techzo.com.example.cambiazo.exchanges.domain.model.commands;

public record CreateProductCommand(String name, String description, String desiredObject, Double price, String image, Boolean boost, Boolean available, Long productCategoryId, Long userId,  Long districtId) {

        public CreateProductCommand {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Name is required");
            }
            if (description == null || description.isBlank()) {
                throw new IllegalArgumentException("Description is required");
            }
            if (desiredObject == null || desiredObject.isBlank()) {
                throw new IllegalArgumentException("Desired Object is required");
            }
            if (price == null) {
                throw new IllegalArgumentException("Price is required");
            }
            if (image == null || image.isBlank()) {
                throw new IllegalArgumentException("Image is required");
            }
            if (boost == null) {
                throw new IllegalArgumentException("Boost is required");
            }
            if (available == null) {
                throw new IllegalArgumentException("Available is required");
            }
            if (productCategoryId == null) {
                throw new IllegalArgumentException("Product Category ID is required");
            }
            if (userId == null) {
                throw new IllegalArgumentException("User ID is required");
            }
            if (districtId == null) {
                throw new IllegalArgumentException("District ID is required");
            }
        }
}
