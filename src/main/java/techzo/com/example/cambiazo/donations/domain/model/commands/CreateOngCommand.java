package techzo.com.example.cambiazo.donations.domain.model.commands;

public record CreateOngCommand(
        String name,
        String type,
        String aboutUs,
        String missionAndVision,
        String supportForm,
        String address,
        String email,
        String phone,
        String logo,
        String website,
        Long categoryOngId
) {
}
