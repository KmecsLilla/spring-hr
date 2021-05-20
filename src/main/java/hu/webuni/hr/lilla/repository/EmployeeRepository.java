package hu.webuni.hr.lilla.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.lilla.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Page<Employee> findBySalaryGreaterThan(Integer minSalary, Pageable pageable);

	List<Employee> findByPositionName(String status);

	List<Employee> findByNameStartingWithIgnoreCase(String nameStartingWith);

	List<Employee> findByStartingToWorkBetween(LocalDateTime startDate, LocalDateTime endDate);

	@Modifying
	@Transactional
//	@Query("UPDATE Employee e"
//			+ "SET e.salary = :minSalary "
//			+ "WHERE e.position.name = :positionName "
//			+ "AND e.salary < :minSalary "
//			+ "AND e.company.id = :companyId")
	@Query("UPDATE Employee e "
			+ "SET e.salary = :minSalary "
			+ "WHERE e.id IN ("
			+ "SELECT e2.id "
			+ "FROM Employee e2 "
			+ "WHERE e2.position.name = :positionName "
			+ "AND e2.salary < :minSalary "
			+ "AND e2.company.id = :companyId)")
	int updateSalaries(String positionName, int minSalary, long companyId);

}
