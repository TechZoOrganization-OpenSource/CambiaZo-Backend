package techzo.com.example.cambiazo.exchanges.application.internal.commandservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.DepartmentNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Department;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.District;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateDistrictCommand;
import techzo.com.example.cambiazo.exchanges.domain.services.DistrictCommandService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.DepartmentRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.DistrictRepository;

import java.util.Optional;

@Service
public class DistrictCommandServiceImpl implements DistrictCommandService {

    private final DistrictRepository districtRepository;

    private final DepartmentRepository departmentRepository;

    public DistrictCommandServiceImpl(DistrictRepository districtRepository, DepartmentRepository departmentRepository) {
        this.districtRepository = districtRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Optional<District>handle(CreateDistrictCommand command) {
        Department department = departmentRepository.findById(command.departmentId())
                .orElseThrow(() -> new DepartmentNotFoundException(command.departmentId()));

        var district = new District(command, department);
        districtRepository.save(district);
        return Optional.of(district);
    }

    @Override
    public boolean handleDeleteDistrict(Long id) {
        Optional<District> district = districtRepository.findById(id);
        if (district.isPresent()) {
            districtRepository.delete(district.get());
            return true;
        } else {
            return false;
        }
    }
}
