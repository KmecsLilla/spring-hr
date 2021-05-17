package hu.webuni.hr.lilla.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import hu.webuni.hr.lilla.model.Employee;

public interface EmployeeService {

	public int getPayRaisePercent(Employee employee);

	@Transactional
	public Employee save(Employee employee);

	@Transactional
	public Employee update(Employee employee);

	public List<Employee> findAll();

	public List<Employee> findBySalaryGreaterThan(Integer minSalary);

	public List<Employee> findByPositionName(String status);

	public List<Employee> findByNameStartingWithIgnoreCase(String nameStartingWith);

	public List<Employee> findByStartingToWorkBetween(LocalDateTime startDate, LocalDateTime endDate);

	public Optional<Employee> findById(long id);

	public void delete(long id);

	Page<Employee> findBySalaryGreaterThan(Integer minSalary, Pageable pageable);

}
