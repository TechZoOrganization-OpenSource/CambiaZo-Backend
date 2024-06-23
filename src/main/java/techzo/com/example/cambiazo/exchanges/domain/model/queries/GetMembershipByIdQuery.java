package techzo.com.example.cambiazo.exchanges.domain.model.queries;

public record GetMembershipByIdQuery(Long id) {
        public GetMembershipByIdQuery {
            if (id == null) {
                throw new IllegalArgumentException("id cannot be null");
            }
        }
}
