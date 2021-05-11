package hu.webuni.hr.lilla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.lilla.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	List<Employee> findBySalaryGreaterThan(Integer minSalary);

}
