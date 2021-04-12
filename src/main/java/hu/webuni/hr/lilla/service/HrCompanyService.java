package hu.webuni.hr.lilla.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import hu.webuni.hr.lilla.model.Company;

@Service
public abstract class HrCompanyService {

	private Map<Long, Company> allCompany = new HashMap<>();
	{

	}
	
	public Company save(Company company) {
		
		allCompany.put(company.getId(), company);
		return company;		
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
