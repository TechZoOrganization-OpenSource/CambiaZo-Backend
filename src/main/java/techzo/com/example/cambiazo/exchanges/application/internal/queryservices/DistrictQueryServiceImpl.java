package techzo.com.example.cambiazo.exchanges.application.internal.queryservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.DepartmentNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Department;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.District;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllDistrictsByDepartmentIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllDistrictsQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetDistrictByIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetDistrictByNameQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.valueobjects.DistrictName;
import techzo.com.example.cambiazo.exchanges.domain.services.DistrictQueryService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.DepartmentRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.DistrictRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictQueryServiceImpl implements DistrictQueryService {

    private final DistrictRepository districtRepository;

    private final DepartmentRepository departmentRepository;

    public DistrictQueryServiceImpl(DistrictRepository districtRepository, DepartmentRepository departmentRepository) {
        this.districtRepository = districtRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<District> handle(GetAllDistrictsQuery query) {
        return districtRepository.findAll();
    }

    @Override
    public List<District>handle(GetAllDistrictsByDepartmentIdQuery query){
        Department department = departmentRepository.findById(query.departmentId())
                .orElseThrow(() -> new DepartmentNotFoundException(query.departmentId()));
        return districtRepository.findAllDistrictsByDepartmentId(department);
    }

    @Override
    public Optional<District>handle(GetDistrictByNameQuery query) {
        DistrictName name = new DistrictName(query.name());
        return districtRepository.findByName(name);
    }


    @Override
    public Optional<District>handle(GetDistrictByIdQuery query){


        return districtRepository.findById(query.id());
    }
}
