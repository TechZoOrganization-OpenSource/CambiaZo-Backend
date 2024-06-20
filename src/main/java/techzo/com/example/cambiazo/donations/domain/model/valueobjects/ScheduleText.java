package techzo.com.example.cambiazo.donations.domain.model.valueobjects;

public record ScheduleText(String text) {
    public ScheduleText {
        if(text == null || text.isBlank()){
            throw new IllegalArgumentException("The text is required.");
        }

    }

    public String getScheduleText(){
        return text;
    }
}
