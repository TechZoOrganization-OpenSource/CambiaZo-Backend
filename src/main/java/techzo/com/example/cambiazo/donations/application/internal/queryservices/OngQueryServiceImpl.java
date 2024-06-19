package techzo.com.example.cambiazo.donations.application.internal.queryservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.donations.domain.model.aggregates.Ong;
import techzo.com.example.cambiazo.donations.domain.model.entities.CategoryOng;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAllOngsQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetOngByIdQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetOngsByCategoryOngIdQuery;
import techzo.com.example.cambiazo.donations.domain.services.OngQueryService;
import techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa.CategoryOngRepository;
import techzo.com.example.cambiazo.donations.infrastructure.persistence.jpa.OngRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OngQueryServiceImpl implements OngQueryService {
    private final OngRepository ongRepository;
    private final CategoryOngRepository categoryOngRepository;

    public OngQueryServiceImpl(OngRepository ongRepository, CategoryOngRepository categoryOngRepository) {
        this.ongRepository = ongRepository;
        this.categoryOngRepository = categoryOngRepository;
    }

    @Override
    public Optional<Ong> handle(GetOngByIdQuery query) {
        return ongRepository.findById(query.Id());
    }

    @Override
    public List<Ong>handle(GetAllOngsQuery query) {
        return ongRepository.findAll();
    }

    @Override
    public List<Ong> handle(GetOngsByCategoryOngIdQuery query) {
        CategoryOng categoryOng = categoryOngRepository.findById(query.categoryOngId())
                .orElseThrow(() -> new IllegalArgumentException("CategoryOng with id " + query.categoryOngId() + " not found"));
        return ongRepository.findByCategoryOngId(categoryOng);
    }

}
