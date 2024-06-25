package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.District;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllDistrictsByDepartmentIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllDistrictsQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetDistrictByIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetDistrictByNameQuery;

import java.util.List;
import java.util.Optional;

public interface DistrictQueryService {

    Optional<District>handle(GetDistrictByIdQuery query);

    List<District> handle(GetAllDistrictsQuery query);

    Optional<District> handle(GetDistrictByNameQuery query);

    List<District>handle(GetAllDistrictsByDepartmentIdQuery query);
}
