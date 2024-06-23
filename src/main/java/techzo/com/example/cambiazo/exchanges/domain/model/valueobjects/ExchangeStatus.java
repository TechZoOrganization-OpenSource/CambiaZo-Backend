package techzo.com.example.cambiazo.exchanges.domain.model.valueobjects;

public record ExchangeStatus(String status) {
    public ExchangeStatus {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("The status is required.");
        }

        if (!status.equals("Pendiente") && !status.equals("Aceptado") && !status.equals("Denegado")) {
            throw new IllegalArgumentException("The status must be Pendiente, Aceptado or Denegado.");
        }
    }

    public String getExchangeStatus() {
        return status;
    }
}
