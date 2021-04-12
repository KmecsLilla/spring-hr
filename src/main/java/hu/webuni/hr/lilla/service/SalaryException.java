package hu.webuni.hr.lilla.service;

import hu.webuni.hr.lilla.dto.EmployeeDto;

public class SalaryException extends RuntimeException {
	
	public SalaryException(EmployeeDto employeeDto) {
		super("Not valid input for salary: " + employeeDto.getSalary());
	}

}
