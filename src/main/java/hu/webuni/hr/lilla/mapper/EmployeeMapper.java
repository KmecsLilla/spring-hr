package hu.webuni.hr.lilla.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.hr.lilla.dto.EmployeeDto;
import hu.webuni.hr.lilla.model.Employee;

@Mapper(componentModel="spring")
public interface EmployeeMapper {

	List<EmployeeDto> allEmployeeToEmployeeDtos(List<Employee> allEmployees);

	@Mapping(target = "companyName", source = "company.name")
	@Mapping(target = "status", source = "position.name")
	EmployeeDto employeeToDto(Employee employee);

	@Mapping(source = "companyName", target = "company.name")
	@Mapping(source = "status", target = "position.name")
	Employee dtoToEmployee(EmployeeDto employeeDto);

	List<Employee> allEmployeeDtosToEmployee(List<EmployeeDto> allEmployees);

}
