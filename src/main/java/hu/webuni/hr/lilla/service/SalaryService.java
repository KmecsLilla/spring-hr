package hu.webuni.hr.lilla.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import hu.webuni.hr.lilla.model.Employee;
import hu.webuni.hr.lilla.repository.PositionRepository;

@Service
public class SalaryService {
	private HrEmployeeService hrEmployeeService;
	private PositionRepository positionRepository;

	public SalaryService(HrEmployeeService hrEmployeeService, PositionRepository positionRepository) {
		super();
		this.hrEmployeeService = hrEmployeeService;
		this.positionRepository = positionRepository;
	}

	public int getRaisedSalary(Employee employee) {
		return (int)(employee.getSalary() * (100 + hrEmployeeService.getPayRaisePercent(employee)) / 100.0);
	}

	@Transactional
	public void raiseMinimalSalary(String positionName, int minSalary) {
		positionRepository.findByName(positionName)
		.forEach(p -> {
			p.setMinSalary(minSalary);
			p.getEmployees().forEach(e -> {
				if (e.getSalary() < minSalary) {
				e.setSalary(minSalary);
				}
			});

		});
	}
}
