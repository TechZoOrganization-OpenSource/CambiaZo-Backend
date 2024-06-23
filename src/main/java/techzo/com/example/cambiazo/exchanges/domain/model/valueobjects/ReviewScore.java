package techzo.com.example.cambiazo.exchanges.domain.model.valueobjects;

public record ReviewScore(Long score) {
    public ReviewScore {
        if(score == null){
            throw new IllegalArgumentException("The score is required.");
        }

        if(score < 0 || score > 5){
            throw new IllegalArgumentException("The score must be between 0 and 5.");
        }
    }

    public Long getReviewScore(){
        return score;
    }
}
