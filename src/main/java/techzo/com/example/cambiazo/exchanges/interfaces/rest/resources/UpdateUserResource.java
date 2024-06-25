package techzo.com.example.cambiazo.exchanges.interfaces.rest.resources;

public record UpdateUserResource(String name, String email, String phone, String password, String profilePicture, Long membershipId) {
}
