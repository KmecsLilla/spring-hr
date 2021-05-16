package hu.webuni.hr.lilla.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Company addEmployee(long companyId, Employee employee) {
		Company company = companyRepository.findById(companyId).get();
		company.addEmployee(employee);
		// companyRepository.save(company); cascade-dal működik
		employeeRepository.save(employee);
		return company;
	}

	public Company deleteEmployee(long id, long employeeId) {
		Company company = companyRepository.findById(id).get();//.orElseThrow(()-> new NoSuchElementException());
		Employee employee = employeeRepository.findById(employeeId).get();
		employee.setCompany(null);
		company.getEmployees().remove(employee);
		employeeRepository.save(employee);
		return company;
	}
}
