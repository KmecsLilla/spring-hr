package hu.webuni.hr.lilla.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.lilla.dto.EmployeeDto;
import hu.webuni.hr.lilla.model.Employee;
import hu.webuni.hr.lilla.repository.EmployeeRepository;

////@Service nem itt valósítottam meg a Service-t, hanem lejjebb, duplakommentbe a SPRING DATA bevezetése miatt került (a többieknél szimpla komment)
@Service
public abstract class HrEmployeeService implements EmployeeService {

//	private Map<Long, Employee> allEmployee = new HashMap<>();
//	{
//		allEmployee.put(1L, new Employee(1L,"Eliza","értékesítési igazgató", 1_500_000,LocalDateTime.of(1999,4,1,1,1,1)));
//		allEmployee.put(2L, new Employee(2L,"Jani", "projektmenedzser", 800_000,  LocalDateTime.of(2002,12,1,1,1,1)));
//		allEmployee.put(3L, new Employee(3L,"Józsi", "csoportvezető", 600_000, LocalDateTime.of(2013,1,4,1,1,1)));
//		allEmployee.put(4L, new Employee(4L,"Pisti", "asszisztens", 500_000, LocalDateTime.of(2017,3,5,1,1,1)));
//		allEmployee.put(5L, new Employee(5L,"Vilma", "junior", 400_000, LocalDateTime.of(2019,12,9,1,1,1)));
//	}
//	
//	public Employee save(Employee employee) {
//		allEmployee.put(employee.getId(), employee);
//		return employee;		
//	}
//	
//	public Employee modify(Employee employee) {
//		if (allEmployee.containsKey(employee.getId())) {
//			allEmployee.put(employee.getId(), employee);
//			return employee;		
//		} else {
//			return null;
//		}
//	}
//	
//	public List<Employee> findAll() {
//		return new ArrayList<>(allEmployee.values());
//	}
//	
//	public Employee findById(long id) {
//		return allEmployee.get(id);
//	}
//	
//	public void delete(long id) {
//		allEmployee.remove(id);
//	}
	
	//SPRING DATA
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee save(Employee employee) {
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
	public List<Employee> findBySalaryGreaterThan(Integer minSalary) {
		return employeeRepository.findBySalaryGreaterThan(minSalary);
	}

	@Override
	public Optional<Employee> findById(long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public void delete(long id) {
		employeeRepository.deleteById(id);
	}
	
}
