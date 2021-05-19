package hu.webuni.hr.lilla.service;



import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.lilla.model.Company;
import hu.webuni.hr.lilla.model.Employee;
import hu.webuni.hr.lilla.model.Position;
import hu.webuni.hr.lilla.model.Qualification;
import hu.webuni.hr.lilla.repository.CompanyRepository;
import hu.webuni.hr.lilla.repository.EmployeeRepository;
import hu.webuni.hr.lilla.repository.PositionRepository;

@Service
public class InitDbService {

	@Autowired
	PositionRepository positionRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Transactional
	public void initDb() {
		Position developer = positionRepository.save(new Position("fejleszto", Qualification.UNIVERSITY, 300000));
		Position tester = positionRepository.save(new Position("tesztelo", Qualification.HIGH_SCHOOL, 200000));

		Employee newEmployee1 = employeeRepository.save(new Employee(null, "Boris", 2000000, LocalDateTime.now()));
		newEmployee1.setPosition(developer);

		Employee newEmployee2 = employeeRepository.save(new Employee(null, "Maris", 2000000, LocalDateTime.now()));
		newEmployee2.setPosition(tester);

		Company newCompany = companyRepository.save(new Company(null, 0, "BarBar", "", null));
		newCompany.addEmployee(newEmployee1);
		newCompany.addEmployee(newEmployee2);
	}
}
