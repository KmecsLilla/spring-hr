package hu.webuni.hr.lilla.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hu.webuni.hr.lilla.config.HrConfigurationProperties;
import hu.webuni.hr.lilla.model.Employee;

public class SmartEmployeeService extends HrEmployeeService {
	@Autowired
	HrConfigurationProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {
		LocalDate startOfWorkDate = employee.getStartingToWork().toLocalDate();
		LocalDate now = LocalDate.now();
		Period period = Period.between(startOfWorkDate, now);
		int monthsPassed = 12 * period.getYears() + period.getMonths();
		if (monthsPassed >= config.getEmployeeAugment().getSmart3().getLimit()) {
			return config.getEmployeeAugment().getSmart3().getPercent();
		} else if (monthsPassed >= config.getEmployeeAugment().getSmart2().getLimit()) {
			return config.getEmployeeAugment().getSmart2().getPercent();
		} else if (monthsPassed >= config.getEmployeeAugment().getSmart1().getLimit()) {
			return config.getEmployeeAugment().getSmart1().getPercent();
		} else {
			return 0;
		}
	}

	@Override
	public List<Employee> findBySalaryGreaterThan(Integer minSalary) {
		// TODO Auto-generated method stub
		return null;
	}
}
