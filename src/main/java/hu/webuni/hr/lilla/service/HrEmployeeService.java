package hu.webuni.hr.lilla.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hu.webuni.hr.lilla.model.Employee;
import hu.webuni.hr.lilla.model.Position;
import hu.webuni.hr.lilla.repository.EmployeeRepository;
import hu.webuni.hr.lilla.repository.PositionRepository;

@Service
public abstract class HrEmployeeService implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	PositionRepository positionRepository;

	@Override
	public Employee save(Employee employee) {
		employee.setCompany(null);
		String positionName = employee.getPosition().getName();
		if (positionName !=null) {
			List<Position> positions = positionRepository.findByName(positionName);
			Position position = null;
			if (positions.isEmpty()) {
				position = positionRepository.save(employee.getPosition());
			} else {
				position = positions.get(0);
			}
			employee.setPosition(position);
		}
		return employeeRepository.save(employee);
	}


	@Override
	public Employee update(Employee employee) {
		if (!employeeRepository.existsById(employee.getId())) {
			return null;
		} else {
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
}
