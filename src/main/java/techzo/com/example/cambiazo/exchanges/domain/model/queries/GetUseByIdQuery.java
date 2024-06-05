package techzo.com.example.cambiazo.exchanges.domain.model.queries;

public record GetUseByIdQuery(Long id) {

    public GetUseByIdQuery{
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
    }
}
