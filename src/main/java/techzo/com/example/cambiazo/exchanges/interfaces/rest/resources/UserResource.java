package techzo.com.example.cambiazo.exchanges.interfaces.rest.resources;

public record UserResource(Long id,String name, String email, String phone, String password, String profilePicture, Long membershipId) {
}
