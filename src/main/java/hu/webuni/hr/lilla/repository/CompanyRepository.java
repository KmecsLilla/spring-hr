package hu.webuni.hr.lilla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.lilla.model.Company;
import hu.webuni.hr.lilla.model.Employee;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	@Query("SELECT c FROM Company c JOIN c.employees e WHERE e.salary > :minSalary" )
	public List<Employee> findEmployeeWithSalaryHigherThan(int minSalary);

	@Query("SELECT c FROM Company c WHERE Size(c.employees) > :minEmployeeCount")
	public List<Employee> findEmployeeCountHigherThan(int minEmployeeCount);

}
