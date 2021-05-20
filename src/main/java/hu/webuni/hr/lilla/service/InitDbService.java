package hu.webuni.hr.lilla.service;



import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.lilla.model.Company;
import hu.webuni.hr.lilla.model.Employee;
import hu.webuni.hr.lilla.model.Position;
import hu.webuni.hr.lilla.model.PositionDetailsByCompany;
import hu.webuni.hr.lilla.model.Qualification;
import hu.webuni.hr.lilla.repository.CompanyRepository;
import hu.webuni.hr.lilla.repository.EmployeeRepository;
import hu.webuni.hr.lilla.repository.PositionDetailsByCompanyRepository;
import hu.webuni.hr.lilla.repository.PositionRepository;

@Service
public class InitDbService {

	@Autowired
	PositionRepository positionRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	PositionDetailsByCompanyRepository positionDetailsByCompanyRepository;

	@Transactional
	public void initDb() {
		Position developer = positionRepository.save(new Position("fejleszto", Qualification.UNIVERSITY));
		Position tester = positionRepository.save(new Position("tesztelo", Qualification.HIGH_SCHOOL));

		Employee newEmployee1 = employeeRepository.save(new Employee(null, "Boris", 200000, LocalDateTime.now()));
		newEmployee1.setPosition(developer);

		Employee newEmployee2 = employeeRepository.save(new Employee(null, "Maris", 200000, LocalDateTime.now()));
		newEmployee2.setPosition(tester);

		Company newCompany = companyRepository.save(new Company(null, 10, "BarBar", "1022 Budapest", null));
		newCompany.addEmployee(newEmployee1);
		newCompany.addEmployee(newEmployee2);

		PositionDetailsByCompany pd = new PositionDetailsByCompany();
		pd.setCompany(newCompany);
		pd.setMinSalary(250000);
		pd.setPosition(developer);
		positionDetailsByCompanyRepository.save(pd);

		PositionDetailsByCompany pd2 = new PositionDetailsByCompany();
		pd2.setCompany(newCompany);
		pd2.setMinSalary(200000);
		pd2.setPosition(tester);
		positionDetailsByCompanyRepository.save(pd2);
	}
}
