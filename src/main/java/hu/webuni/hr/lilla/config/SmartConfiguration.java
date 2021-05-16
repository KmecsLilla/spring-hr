package hu.webuni.hr.lilla.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.lilla.service.HrEmployeeService;
import hu.webuni.hr.lilla.service.SmartEmployeeService;

@Configuration
@Profile("smart")
public class SmartConfiguration {
	@Bean
	public HrEmployeeService hrEmployeeService() {
		return new SmartEmployeeService();
	}
}
