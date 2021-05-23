package hu.webuni.hr.lilla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.lilla.model.AverageSalaryByPosition;
import hu.webuni.hr.lilla.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	@Query("SELECT c FROM Company c JOIN c.employees e WHERE e.salary > :minSalary" )
	public List<Company> findEmployeeWithSalaryHigherThan(int minSalary);

	@Query("SELECT c FROM Company c WHERE Size(c.employees) > :minEmployeeCount")
	public List<Company> findEmployeeCountHigherThan(int minEmployeeCount);

	@Query("SELECT e.position.name AS position, AVG(e.salary) AS averageSalary " //"SELECT new hu.webuni.hr.lilla.model.MyModel(e.status, AVG(e.salary))" ekkor List<MyModel >metódusban
			+ "FROM Company c "
			+ "JOIN c.employees e "
			+ "WHERE c.id = :companyId "
			+ "GROUP BY e.position.name " //e.status
			+ "ORDER BY AVG(e.salary) DESC")
	public List <AverageSalaryByPosition> findAverageSalariesByPosition(long companyId);

	//@Query("SELECT DISTINCT c FROM Company c LEFT JOIN FETCH c.employees")
	//@EntityGraph(attributePaths = {"employees"})
	@EntityGraph("Company.full")
	@Query("SELECT c FROM Company c")
	public List<Company> findAllWithEmployees();
}
