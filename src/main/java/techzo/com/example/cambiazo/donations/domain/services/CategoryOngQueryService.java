package techzo.com.example.cambiazo.donations.domain.services;

import techzo.com.example.cambiazo.donations.domain.model.entities.CategoryOng;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAllCategoryOngsQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetCategoryOngByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CategoryOngQueryService {
    List<CategoryOng> handle(GetAllCategoryOngsQuery query);
    Optional<CategoryOng> handle(GetCategoryOngByIdQuery query);
}
