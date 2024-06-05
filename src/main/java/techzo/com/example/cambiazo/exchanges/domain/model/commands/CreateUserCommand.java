package techzo.com.example.cambiazo.exchanges.domain.model.commands;

public record CreateUserCommand(String name, String email, String phone, String password, String profilePicture) {
    public CreateUserCommand{
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email is required");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("phone is required");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password is required");
        }
        if (profilePicture == null || profilePicture.isBlank()) {
            throw new IllegalArgumentException("profilePicture is required");
        }
    }
}
