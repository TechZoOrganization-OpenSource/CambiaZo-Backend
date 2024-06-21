package techzo.com.example.cambiazo.donations.domain.model.commands;

public record CreateScheduleCommand(String text, Long ongId) {
    public CreateScheduleCommand{
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Text cannot be null or empty");
        }
        if (ongId == null) {
            throw new IllegalArgumentException("OngId cannot be null");
        }
    }
}
