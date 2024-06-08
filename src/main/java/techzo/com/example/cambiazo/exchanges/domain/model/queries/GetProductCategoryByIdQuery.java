package techzo.com.example.cambiazo.exchanges.domain.model.queries;

public record GetProductCategoryByIdQuery(Long id) {
    public GetProductCategoryByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
    }
}
