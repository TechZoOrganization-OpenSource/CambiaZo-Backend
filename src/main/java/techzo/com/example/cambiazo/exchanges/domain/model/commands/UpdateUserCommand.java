package techzo.com.example.cambiazo.exchanges.domain.model.commands;

public record UpdateUserCommand(Long id, String name, String email, String phone, String password, String profilePicture, Long membershipId) {
}
