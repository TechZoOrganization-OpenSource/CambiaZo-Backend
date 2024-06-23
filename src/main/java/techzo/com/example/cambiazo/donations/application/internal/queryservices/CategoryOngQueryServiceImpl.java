package techzo.com.example.cambiazo.donations.application.internal.queryservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.donations.domain.model.entities.CategoryOng;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAllCategoryOngsQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetCategoryOngByIdQuery;
import techzo.com.example.cambiazo.donations.domain.services.CategoryOngQueryService;
import techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa.CategoryOngRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryOngQueryServiceImpl implements CategoryOngQueryService {
    private final CategoryOngRepository categoryOngRepository;

    public CategoryOngQueryServiceImpl(CategoryOngRepository categoryOngRepository) {
        this.categoryOngRepository = categoryOngRepository;
    }

    @Override
    public List<CategoryOng> handle(GetAllCategoryOngsQuery query) {
        return categoryOngRepository.findAll();
    }

    @Override
    public Optional<CategoryOng> handle(GetCategoryOngByIdQuery query) {
        return categoryOngRepository.findById(query.id());
    }
}
