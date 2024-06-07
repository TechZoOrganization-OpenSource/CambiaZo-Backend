package techzo.com.example.cambiazo.donations.domain.services;

import techzo.com.example.cambiazo.donations.domain.model.aggregates.CategoryOng;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateCategoryOngCommand;

import java.util.Optional;

public interface CategoryOngCommandService {
    Optional<CategoryOng> handle(CreateCategoryOngCommand command);
}
