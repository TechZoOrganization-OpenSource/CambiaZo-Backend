package techzo.com.example.cambiazo.donations.application.internal.commandservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.donations.domain.model.entities.CategoryOng;
import techzo.com.example.cambiazo.donations.domain.model.commands.CreateCategoryOngCommand;
import techzo.com.example.cambiazo.donations.domain.services.CategoryOngCommandService;
import techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa.CategoryOngRepository;

import java.util.Optional;

@Service
public class CategoryOngCommandServiceImpl implements CategoryOngCommandService {
    private final CategoryOngRepository categoryOngRepository;

    public CategoryOngCommandServiceImpl(CategoryOngRepository categoryOngRepository) {
        this.categoryOngRepository = categoryOngRepository;
    }

    @Override
    public Optional<CategoryOng>handle (CreateCategoryOngCommand command) {
        if (categoryOngRepository.existsByName(command.name())) {
            throw new IllegalArgumentException("Category Ong with same name already exists");
        }
        var categoryOng = new CategoryOng(command);
        var createdCategoryOng = categoryOngRepository.save(categoryOng);
        return Optional.of(createdCategoryOng);
    }
}
