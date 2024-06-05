package techzo.com.example.cambiazo.exchanges.interfaces.rest.resources;

public record CreateUserResource(String name, String email, String phone, String password, String profilePicture) {
    public CreateUserResource {
        //the same but only null validation
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if (email == null) {
            throw new IllegalArgumentException("email cannot be null");
        }
        if (phone == null) {
            throw new IllegalArgumentException("phone cannot be null");
        }
        if (password == null) {
            throw new IllegalArgumentException("password cannot be null");
        }
        if (profilePicture == null) {
            throw new IllegalArgumentException("profilePicture cannot be null");
        }
    }
}
