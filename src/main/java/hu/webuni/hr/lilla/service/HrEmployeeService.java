package hu.webuni.hr.lilla.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import hu.webuni.hr.lilla.model.Employee;
import hu.webuni.hr.lilla.repository.EmployeeRepository;

@Service
public abstract class HrEmployeeService implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	PositionService positionService;

	@Override
	public Employee save(Employee employee) {
		clearCompanyAndSetPosition(employee);
		return employeeRepository.save(employee);
	}


	private void clearCompanyAndSetPosition(Employee employee) {
		employee.setCompany(null);
		positionService.assignPosition(employee);
	}


//	private void assignPosition(Employee employee) {  átkerül a PositionService-be
//		String positionName = employee.getPosition().getName();
//		if (positionName !=null) {
//			List<Position> positions = positionRepository.findByName(positionName);
//			Position position = null;
//			if (positions.isEmpty()) {
//				position = positionRepository.save(employee.getPosition());
//			} else {
//				position = positions.get(0);
//			}
//			employee.setPosition(position);
//		}
//	}


	@Override
	public Employee update(Employee employee) {
		if (!employeeRepository.existsById(employee.getId())) {
			return null;
		} else {
			clearCompanyAndSetPosition(employee);
			return employeeRepository.save(employee);
		}
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}



	@Override
	public Optional<Employee> findById(long id) {
		return employeeRepository.findById(id);
	}


	@Override
	public List<Employee> findByPositionName(String status) {
		return employeeRepository.findByPositionName(status);
	}

	@Override
	public List<Employee> findByNameStartingWithIgnoreCase(String nameStartingWith){
		return employeeRepository.findByNameStartingWithIgnoreCase(nameStartingWith);
	}

	@Override
	public List<Employee> findByStartingToWorkBetween(LocalDateTime startDate, LocalDateTime endDate) {
		return employeeRepository.findByStartingToWorkBetween(startDate, endDate);
	}

	@Override
	public void delete(long id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public Page<Employee> findBySalaryGreaterThan(Integer minSalary, Pageable pageable) {
		return employeeRepository.findBySalaryGreaterThan(minSalary, pageable);
	}

	@Override
	public List<Employee> findEmployeesByExample(Employee example) {
		long id = example.getId();
		String name = example.getName();
		String status = example.getPosition().getName();
		int salary = example.getSalary();
		LocalDateTime startingToWork = example.getStartingToWork();
		String companyName = example.getCompany().getName();

		Specification<Employee> spec = Specification.where(null);

		if (id > 0) {
			spec = spec.and(EmployeeSpecification.hasId(id));
		}

		if (StringUtils.hasText(name)) {
			spec = spec.and(EmployeeSpecification.hasName(name));
		}

		if (StringUtils.hasText(status)) {
			spec = spec.and(EmployeeSpecification.hasStatus(status));
		}

		if (salary > 0) {
			spec = spec.and(EmployeeSpecification.hasSalary(salary));
		}

		if (startingToWork != null) {
			spec = spec.and(EmployeeSpecification.hasStartingToWork(startingToWork));
		}

		if (StringUtils.hasText(companyName)) {
			spec = spec.and(EmployeeSpecification.hasCompanyName(companyName));
		}

		return employeeRepository.findAll(spec, Sort.by("id"));
	}
}
