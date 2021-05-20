package hu.webuni.hr.lilla.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import hu.webuni.hr.lilla.model.Employee;
import hu.webuni.hr.lilla.repository.EmployeeRepository;
import hu.webuni.hr.lilla.repository.PositionDetailsByCompanyRepository;
import hu.webuni.hr.lilla.repository.PositionRepository;

@Service
public class SalaryService {
	private HrEmployeeService hrEmployeeService;
	private PositionRepository positionRepository;
	private PositionDetailsByCompanyRepository positionDetailsByCompanyRepository;
	EmployeeRepository employeeRepository;



	public SalaryService(HrEmployeeService hrEmployeeService, PositionRepository positionRepository,
			PositionDetailsByCompanyRepository positionDetailsByCompanyRepository,
			EmployeeRepository employeeRepository) {
		this.hrEmployeeService = hrEmployeeService;
		this.positionRepository = positionRepository;
		this.positionDetailsByCompanyRepository = positionDetailsByCompanyRepository;
		this.employeeRepository = employeeRepository;
	}

	public int getRaisedSalary(Employee employee) {
		return (int)(employee.getSalary() * (100 + hrEmployeeService.getPayRaisePercent(employee)) / 100.0);
	}

//	@Transactional
//	public void raiseMinimalSalary(String positionName, int minSalary) {
//		positionRepository.findByName(positionName)
//		.forEach(p -> {
//			p.setMinSalary(minSalary);
//			p.getEmployees().forEach(e -> {
//				if (e.getSalary() < minSalary) {
//					e.setSalary(minSalary);
//				}
//			});
//		});
//	}

	@Transactional
	public void raiseMinimalSalary(String positionName, int minSalary, long companyId) {
		positionDetailsByCompanyRepository.findByPositionNameAndCompanyId(positionName, companyId)
		.forEach(pd -> {
			pd.setMinSalary(minSalary);
//			pd.getCompany().getEmployees().forEach(e -> {
//				if (e.getPosition().getName().equals(positionName)
//						&& e.getSalary() < minSalary) {
//					e.setSalary(minSalary);
//				}
//			});
		});

		employeeRepository.updateSalaries(positionName, minSalary, companyId);
	}
}


