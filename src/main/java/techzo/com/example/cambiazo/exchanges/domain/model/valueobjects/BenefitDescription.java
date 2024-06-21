package techzo.com.example.cambiazo.exchanges.domain.model.valueobjects;

public record BenefitDescription(String description) {

    public BenefitDescription{
        if(description == null || description.isEmpty()){
            throw new IllegalArgumentException("Description is mandatory");
        }
    }

    public String getBenefitDescription(){
        return description;
    }
}
