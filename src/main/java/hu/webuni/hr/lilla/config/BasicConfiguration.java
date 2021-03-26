package hu.webuni.hr.lilla.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.lilla.service.DefaultEmployeeService;
import hu.webuni.hr.lilla.service.EmployeeService;

@Configuration
@Profile("basic")
public class BasicConfiguration {
	
	
	@Bean 
	public EmployeeService employeeService() {
		return new DefaultEmployeeService();
	}
}
