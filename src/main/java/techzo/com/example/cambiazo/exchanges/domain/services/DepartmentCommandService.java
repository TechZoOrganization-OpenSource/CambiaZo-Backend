package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Department;
import techzo.com.example.cambiazo.exchanges.domain.model.commands.CreateDepartmentCommand;

import java.util.Optional;

public interface DepartmentCommandService {

    Optional<Department> handle(CreateDepartmentCommand command);

    boolean handleDeleteDepartment(Long id);
}
