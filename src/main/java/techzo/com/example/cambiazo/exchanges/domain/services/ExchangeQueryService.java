package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Exchange;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ExchangeQueryService {

    Optional<Exchange>handle(GetExchangeByIdQuery query);

    List<Exchange>handle(GetAllExchangesQuery query);

    List<Exchange>handle(GetAllExchangesByProductChangeQuery query);

    List<Exchange>handle(GetAllExchangesByProductOwnQuery query);

    List<Exchange>handle(GetAllExchangesByUserChangeQuery query);

    List<Exchange>handle(GetAllExchangesByUserOwnQuery query);
}
