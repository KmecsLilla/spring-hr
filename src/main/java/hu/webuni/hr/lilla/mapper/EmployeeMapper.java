package hu.webuni.hr.lilla.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.hr.lilla.dto.EmployeeDto;
import hu.webuni.hr.lilla.model.Employee;

@Mapper(componentModel="spring")
public interface EmployeeMapper {

	List<EmployeeDto> allEmployeeToEmployeeDtos(List<Employee> allEmployees);
	
	EmployeeDto employeeToDto(Employee employee);

	Employee dtoToEmployee(EmployeeDto employeeDto);

}
