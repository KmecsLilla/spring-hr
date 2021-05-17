package hu.webuni.hr.lilla.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.lilla.dto.EmployeeDto;
import hu.webuni.hr.lilla.mapper.EmployeeMapper;
import hu.webuni.hr.lilla.model.Employee;
import hu.webuni.hr.lilla.service.HrEmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	@Autowired
	HrEmployeeService hrEmployeeService;

	@Autowired
	EmployeeMapper employeeMapper;

	@GetMapping
	public List<EmployeeDto> getEmployees(@RequestParam(required = false) Integer minSalary, Pageable pageable) {
		List<Employee> employees = null;
		if (minSalary == null) {
			return employeeMapper.allEmployeeToEmployeeDtos(hrEmployeeService.findAll());
		} else {
			Page<Employee> page = hrEmployeeService.findBySalaryGreaterThan(minSalary, pageable);
			employees = page.getContent();
			System.out.println(page.getNumber());
			System.out.println(page.getNumberOfElements());
			System.out.println(page.getSize());
			System.out.println(page.getTotalElements());
			System.out.println(page.getTotalPages());
			System.out.println(page.isFirst());
		}
		return employeeMapper.allEmployeeToEmployeeDtos(employees);
	}

	@GetMapping("/{id}")
	public EmployeeDto getById(@PathVariable long id) {
		EmployeeDto employeeDto = employeeMapper.employeeToDto(hrEmployeeService.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)));
		return employeeDto;
	}

	@GetMapping("/status/{status}")
	public List<EmployeeDto> getEmployeesByStatus(@PathVariable String status) {
		return employeeMapper.allEmployeeToEmployeeDtos(hrEmployeeService.findByStatus(status));
	}

	@GetMapping("/namestartingwith/{nameStartingWith}")
	public List<EmployeeDto> getEmployeesByNameStartingWith(@PathVariable String nameStartingWith) {
		return employeeMapper.allEmployeeToEmployeeDtos(hrEmployeeService.findByNameStartingWithIgnoreCase(nameStartingWith));
	}

	@GetMapping("/filterstartingtowork")
	public List<EmployeeDto> getEmployeesByStartingToWork(@RequestParam String startDateStr, @RequestParam String endDateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime startDate = LocalDateTime.parse(startDateStr, formatter);
		LocalDateTime endDate = LocalDateTime.parse(endDateStr, formatter);
		return employeeMapper
				.allEmployeeToEmployeeDtos(hrEmployeeService
						.findByStartingToWorkBetween(startDate, endDate));
	}

	@PostMapping
	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
		Employee employee = employeeMapper.dtoToEmployee(employeeDto);
		return employeeMapper.employeeToDto(hrEmployeeService.save(employee));
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) {
		employeeDto.setId(id);
		Employee employee = employeeMapper.dtoToEmployee(employeeDto);
		employee = hrEmployeeService.update(employee);
		if (employee != null) {
			employeeDto = employeeMapper.employeeToDto(employee);
			return ResponseEntity.ok(employeeDto) ;
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		hrEmployeeService.delete(id);
	}

	@PostMapping("/payraise")
	public int getPayRaise(@RequestBody @Valid Employee employee) {
		return hrEmployeeService.getPayRaisePercent(employee);
	}
}
