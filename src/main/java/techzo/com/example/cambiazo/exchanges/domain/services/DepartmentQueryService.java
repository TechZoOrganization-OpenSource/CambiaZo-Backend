package techzo.com.example.cambiazo.exchanges.domain.services;

import techzo.com.example.cambiazo.exchanges.domain.model.aggregates.Department;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllDepartmentsByCountryIdQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetAllDepartmentsQuery;
import techzo.com.example.cambiazo.exchanges.domain.model.queries.GetDepartmentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface DepartmentQueryService {

    Optional<Department>handle(GetDepartmentByIdQuery query);

    List<Department>handle(GetAllDepartmentsQuery query);

    List<Department>handle(GetAllDepartmentsByCountryIdQuery query);
}
