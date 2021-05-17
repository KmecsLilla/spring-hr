package hu.webuni.hr.lilla.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hu.webuni.hr.lilla.config.HrConfigurationProperties;
import hu.webuni.hr.lilla.model.Employee;

public class DefaultEmployeeService extends HrEmployeeService {
	@Autowired
	HrConfigurationProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {
		return config.getEmployeeAugment().getDef().getPercent();
	}

	@Override
	public List<Employee> findBySalaryGreaterThan(Integer minSalary) {
		// TODO Auto-generated method stub
		return null;
	}
}
