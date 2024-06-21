package techzo.com.example.cambiazo.exchanges.application.internal.commandservices;

import org.springframework.stereotype.Service;
import techzo.com.example.cambiazo.exchanges.domain.exceptions.CountryNotFoundException;
import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Department;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateDepartmentCommand;
import techzo.com.example.cambiazo.exchanges.domain.model.entities.Country;
import techzo.com.example.cambiazo.exchanges.domain.services.DepartmentCommandService;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.CountryRepository;
import techzo.com.example.cambiazo.exchanges.infrastructure.persistence.jpa.DepartmentRepository;

import java.util.Optional;

@Service
public class DepartmentCommandServiceImpl implements DepartmentCommandService {

    private final DepartmentRepository departmentRepository;

    private final CountryRepository countryRepository;

    public DepartmentCommandServiceImpl(DepartmentRepository departmentRepository, CountryRepository countryRepository) {
        this.departmentRepository = departmentRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Department>handle(CreateDepartmentCommand command) {
        Country country = countryRepository.findById(command.countryId())
                .orElseThrow(() -> new CountryNotFoundException(command.countryId()));

        var department = new Department(command, country);
        departmentRepository.save(department);
        return Optional.of(department);
    }

    @Override
    public boolean handleDeleteDepartment(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            departmentRepository.delete(department.get());
            return true;
        } else {
            return false;
        }
    }
}
