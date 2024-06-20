package techzo.com.example.cambiazo.donations.domain.model.commands;

public record CreateAccountNumberCommand(String name, String cci, String account, Long ongId) {
}
