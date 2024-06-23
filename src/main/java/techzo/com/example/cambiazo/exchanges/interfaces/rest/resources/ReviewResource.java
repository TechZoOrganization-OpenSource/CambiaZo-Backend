package techzo.com.example.cambiazo.exchanges.interfaces.rest.resources;

public record ReviewResource(Long id,String message, Long score, String status, Long exchangeId, Long userAuthorId, Long userReceptorId) {
}
