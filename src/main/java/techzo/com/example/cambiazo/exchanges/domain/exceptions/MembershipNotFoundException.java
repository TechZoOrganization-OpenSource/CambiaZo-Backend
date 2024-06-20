package techzo.com.example.cambiazo.exchanges.domain.exceptions;

public class MembershipNotFoundException extends RuntimeException{

    public MembershipNotFoundException(Long membershipId) {
        super("Membership with id " + membershipId + " not found");
    }
}
