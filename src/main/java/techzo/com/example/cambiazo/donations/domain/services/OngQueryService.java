package techzo.com.example.cambiazo.donations.domain.services;



import techzo.com.example.cambiazo.donations.domain.model.aggregates.Ong;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAllOngsQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetOngByIdQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetOngsByCategoryOngIdQuery;

import java.util.List;
import java.util.Optional;

public interface OngQueryService {
    Optional<Ong> handle(GetOngByIdQuery query);
    List<Ong> handle(GetAllOngsQuery query);

    List<Ong>handle(GetOngsByCategoryOngIdQuery query);


}
