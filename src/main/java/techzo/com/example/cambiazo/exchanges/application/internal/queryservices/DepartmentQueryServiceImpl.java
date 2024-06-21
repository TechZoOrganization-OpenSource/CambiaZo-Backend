package techzo.com.example.cambiazo.exchanges.application.internal.queryservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.CountryNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Department;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Country;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllDepartmentsByCountryIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllDepartmentsQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetDepartmentByIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.services.DepartmentQueryService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.CountryRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentQueryServiceImpl implements DepartmentQueryService {

    private final DepartmentRepository departmentRepository;

    private final CountryRepository countryRepository;

    public DepartmentQueryServiceImpl(DepartmentRepository departmentRepository, CountryRepository countryRepository) {
        this.departmentRepository = departmentRepository;
        this.countryRepository=countryRepository;
    }

    @Override
    public List<Department> handle(GetAllDepartmentsQuery query) {
        return departmentRepository.findAll();
    }

    @Override
    public List<Department>handle(GetAllDepartmentsByCountryIdQuery query){
        Country country = countryRepository.findById(query.countryId())
                .orElseThrow(() -> new CountryNotFoundException(query.countryId()));
        return departmentRepository.findAllDepartmentsByCountryId(country);
    }

    @Override
    public Optional<Department>handle(GetDepartmentByIdQuery query){
        return departmentRepository.findById(query.id());
    }
}
