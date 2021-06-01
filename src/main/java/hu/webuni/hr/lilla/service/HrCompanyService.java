package hu.webuni.hr.lilla.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.lilla.model.AverageSalaryByPosition;
import hu.webuni.hr.lilla.model.Company;
import hu.webuni.hr.lilla.model.Employee;
import hu.webuni.hr.lilla.repository.CompanyRepository;
import hu.webuni.hr.lilla.repository.EmployeeRepository;

@Service
public class HrCompanyService {
	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	PositionService positionService;

	public Company save(Company company) {
		return companyRepository.save(company);
	}

	public Company update(Company company) {
		if (!companyRepository.existsById(company.getId())) {
			return null;
		} else {
			return companyRepository.save(company);
		}
	}

	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	public Optional<Company> findById(long id) {
		return companyRepository.findById(id);
	}

	public void delete(long id) {
		companyRepository.deleteById(id);
	}

	@Transactional
	public Company addEmployee(long companyId, Employee employee) {
//		Company company = companyRepository.findById(companyId).get();
		Company company = companyRepository.findWithEmployeesById(companyId).get();
		positionService.assignPosition(employee);
		company.addEmployee(employee);
		// companyRepository.save(company); cascade-dal működik
		Employee savedEmployee = employeeRepository.save(employee);
		company.addEmployee(savedEmployee);
		return company;
	}

	@Transactional
	public Company deleteEmployee(long id, long employeeId) {
		Company company = companyRepository.findById(id).get();
		Employee employee = employeeRepository.findById(employeeId).get();
		employee.setCompany(null);
		company.getEmployees().remove(employee);
//		employeeRepository.save(employee); //mert menedzselt!
		return company;
	}

	@Transactional
	public Company replaceEmployees(long id, List<Employee> employees) {
		Company company = companyRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
		company.getEmployees().stream().forEach(e -> {
			e.setCompany(null);
		});
		company.getEmployees().clear();

		for (Employee employee : employees) {
//			company.addEmployee(employee);
//			positionService.assignPosition(employee);
//			employeeRepository.save(employee);

			positionService.assignPosition(employee);
			company.addEmployee(employeeRepository.save(employee));
		}
		return company;
	}

	public List<Company> findByEmployeeWithSalaryHigherThan(int aboveSalary) {
		List<Company> foundCompanies = companyRepository.findEmployeeWithSalaryHigherThan(aboveSalary);
		return foundCompanies;
	}

	public List<Company> findEmployeeCountHigherThan(int aboveEmployeeNumber) {
		List<Company> foundCompanies = companyRepository.findEmployeeCountHigherThan(aboveEmployeeNumber);
		return foundCompanies;
	}

	public List<AverageSalaryByPosition> findAverageSalariesByPosition(long id) {
		List<AverageSalaryByPosition> averageSalaries = companyRepository.findAverageSalariesByPosition(id);
		return averageSalaries;
	}

	public List<Company> findAllWithEmployees() {
		List<Company> allCompany = companyRepository.findAllWithEmployees();
		return allCompany;
	}
}
