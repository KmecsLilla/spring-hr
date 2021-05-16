package hu.webuni.hr.lilla.service;

import hu.webuni.hr.lilla.dto.EmployeeDto;

public class StartToWorkException extends RuntimeException{

	public StartToWorkException(EmployeeDto employeeDto) {
		super("Not valid input for starting to work: " + employeeDto.getStartingToWork());
	}
}
