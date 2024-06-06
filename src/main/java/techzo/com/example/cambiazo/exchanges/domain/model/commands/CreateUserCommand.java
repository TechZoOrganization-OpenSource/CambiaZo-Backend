package techzo.com.example.cambiazo.exchanges.domain.model.commands;

public record CreateUserCommand(String name, String email, String phone, String password, String profilePicture) {
    public CreateUserCommand{
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email cannot be null or empty");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("phone cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password cannot be null or empty");
        }
        if (profilePicture == null || profilePicture.isBlank()) {
            throw new IllegalArgumentException("profilePicture cannot be null or empty");
        }
    }
}
