package hu.webuni.hr.lilla.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.lilla.model.Company;
import hu.webuni.hr.lilla.model.Employee;
import hu.webuni.hr.lilla.repository.CompanyRepository;
import hu.webuni.hr.lilla.repository.EmployeeRepository;

//SPRINg DATA előtt
//@Service
//public class HrCompanyService {
//	
//	private Map<Long, Company> allCompany = new HashMap<>();
//	{
//
//		List<Employee> employeesOfCompany1 = new ArrayList<>();
//		employeesOfCompany1.add(new Employee(1L,"Eliza","értékesítési igazgató", 1_500_000,LocalDateTime.of(1999,4,1,1,1,1)));
//		employeesOfCompany1.add(new Employee(2L, "Pisti", "asszisztens", 500_000, LocalDateTime.of(2017,3,5,1,1,1)));
//		
//		List<Employee> employeesOfCompany2 = new ArrayList<>();
//		employeesOfCompany2.add(new Employee(1L, "Józsi", "csoportvezető", 600_000, LocalDateTime.of(2013,1,4,1,1,1)));
//		employeesOfCompany2.add(new Employee(2L, "Vilma", "junior", 400_000, LocalDateTime.of(2019,12,9,1,1,1)));
//			
//		allCompany.put(1L, new Company(1L, 1L, "Libretto", "Budapest", employeesOfCompany1));
//		allCompany.put(2L, new Company(2L, 2L, "Librarius", "Miskolc", employeesOfCompany2));
//	}
//	
//	public Company save(Company company) {	
//		allCompany.put(company.getId(), company);
//		return company;
//	}
//	
//	public Company modify(Company company) {
//		if (allCompany.containsKey(company.getRegistrationNumber())) {
//			allCompany.put(company.getRegistrationNumber(), company);
//			return company;		
//		} else {
//			return null;
//		}
//	}
//	
//	public List<Company> findAll() {
//		return new ArrayList<>(allCompany.values());
//	}
//	
//	public Company findById(long id) {
//		return allCompany.get(id);
//	}
//	
//	public void delete(long id) {
//		allCompany.remove(id);
//	}
//}

//SPRING DATA
@Service
public class HrCompanyService {
	
	@Autowired 
	CompanyRepository companyRepository;
	
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

	public Company addEmployee(long id, Employee employee) {
		Company company = companyRepository.findById(id).get();
		company.addEmployeesOfCompany(employee);
		companyRepository.save(company);
		return company;
	}
	
//	@Autowired
//	EmployeeRepository employeeRepository;
//	
//
//	@Override
//	public List<Employee> findAll() {
//		return employeeRepository.findAll();
//	}
//	
//	@Override
//	public List<Employee> findBySalaryGreaterThan(Integer minSalary) {
//		return employeeRepository.findBySalaryGreaterThan(minSalary);
//	}
//
//	@Override
//	public Optional<Employee> findById(long id) {
//		return employeeRepository.findById(id);
//	}
//	
//	@Override 
//	public List<Employee> findByStatus(String status) {
//		return employeeRepository.findByStatus(status);
//	}
//	
//	@Override 
//	public List<Employee> findByNameStartingWithIgnoreCase(String nameStartingWith){
//		return employeeRepository.findByNameStartingWithIgnoreCase(nameStartingWith);
//	}
//	
//	@Override 
//	public List<Employee> findByStartingToWorkBetween(LocalDateTime startDate, LocalDateTime endDate) {
//		return employeeRepository.findByStartingToWorkBetween(startDate, endDate);
//	}
//	
//	@Override
//	public void delete(long id) {
//		employeeRepository.deleteById(id);
//	}
}
