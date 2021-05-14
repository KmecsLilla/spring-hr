package hu.webuni.hr.lilla.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.lilla.model.Company;
import hu.webuni.hr.lilla.model.Employee;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	
}
