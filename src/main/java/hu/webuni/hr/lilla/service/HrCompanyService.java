package hu.webuni.hr.lilla.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import hu.webuni.hr.lilla.model.Company;
import hu.webuni.hr.lilla.model.Employee;

@Service
public class HrCompanyService {
	
	private Map<Long, Company> allCompany = new HashMap<>();
	{

		List<Employee> employeesOfCompany1 = new ArrayList<>();
		employeesOfCompany1.add(new Employee(1L,"Eliza","értékesítési igazgató", 1_500_000,LocalDateTime.of(1999,4,1,1,1,1)));
		employeesOfCompany1.add(new Employee(2L, "Pisti", "asszisztens", 500_000, LocalDateTime.of(2017,3,5,1,1,1)));
		
		List<Employee> employeesOfCompany2 = new ArrayList<>();
		employeesOfCompany2.add(new Employee(1L, "Józsi", "csoportvezető", 600_000, LocalDateTime.of(2013,1,4,1,1,1)));
		employeesOfCompany2.add(new Employee(2L, "Vilma", "junior", 400_000, LocalDateTime.of(2019,12,9,1,1,1)));
			
		allCompany.put(1L, new Company(1L, 1L, "Libretto", "Budapest", employeesOfCompany1));
		allCompany.put(2L, new Company(2L, 2L, "Librarius", "Miskolc", employeesOfCompany2));
	}
	
	public Company save(Company company) {	
		allCompany.put(company.getId(), company);
		return company;
	}
	
	public Company modify(Company company) {
		if (allCompany.containsKey(company.getRegistrationNumber())) {
			allCompany.put(company.getRegistrationNumber(), company);
			return company;		
		} else {
			return null;
		}
	}
	
	public List<Company> findAll() {
		return new ArrayList<>(allCompany.values());
	}
	
	public Company findById(long id) {
		return allCompany.get(id);
	}
	
	public void delete(long id) {
		allCompany.remove(id);
	}
}
