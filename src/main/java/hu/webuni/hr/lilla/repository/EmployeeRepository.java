package hu.webuni.hr.lilla.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.lilla.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Page<Employee> findBySalaryGreaterThan(Integer minSalary, Pageable pageable);

	List<Employee> findByStatus(String status);

	List<Employee> findByNameStartingWithIgnoreCase(String nameStartingWith);

	List<Employee> findByStartingToWorkBetween(LocalDateTime startDate, LocalDateTime endDate);
}
