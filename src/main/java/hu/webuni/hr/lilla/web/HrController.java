package hu.webuni.hr.lilla.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import hu.webuni.hr.lilla.dto.EmployeeDto;
import hu.webuni.hr.lilla.model.Employee;
import hu.webuni.hr.lilla.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class HrController {
	
	private Map<Long, EmployeeDto> allEmployee = new HashMap<>();
	{
		allEmployee.put(1L, new EmployeeDto(1L,"Eliza","értékesítési igazgató", 1_500_000,LocalDateTime.of(1999,4,1,1,1,1)));
		allEmployee.put(2L, new EmployeeDto(2L,"Jani", "projektmenedzser", 800_000,  LocalDateTime.of(2002,12,1,1,1,1)));
		allEmployee.put(3L, new EmployeeDto(3L,"Józsi", "csoportvezető", 600_000, LocalDateTime.of(2013,1,4,1,1,1)));
		allEmployee.put(4L, new EmployeeDto(4L,"Pisti", "asszisztens", 500_000, LocalDateTime.of(2017,3,5,1,1,1)));
		allEmployee.put(5L, new EmployeeDto(5L,"Vilma", "junior", 400_000, LocalDateTime.of(2019,12,9,1,1,1)));
	}
	
	
	@Autowired
	EmployeeService employeeService;

//	@GetMapping
//	public List<EmployeeDto> getAll() {
//		return new ArrayList<>(allEmployee.values());
//	}
//	
//	
//	@GetMapping (params = "minSalary")
//	public List<EmployeeDto> getEmployeesBySalary(@RequestParam int minSalary) {
//		return allEmployee.values().stream()
//				.filter(e -> e.getSalary() > minSalary)
//				.collect(Collectors.toList());
//	}	
	
	
	@GetMapping
	public List<EmployeeDto> getEmployees(@RequestParam(required = false) Integer minSalary) {
		if (minSalary == null) {
			return new ArrayList<>(allEmployee.values());
		}
		else
			return allEmployee.values().stream()
				.filter(e -> e.getSalary() > minSalary)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getById(@PathVariable long id) {
		EmployeeDto employeeDto = allEmployee.get(id);
		if (employeeDto != null) {
			return ResponseEntity.ok(employeeDto);
		}
		else 
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
		allEmployee.put(employeeDto.getId(), employeeDto);
		return employeeDto;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
		if (!allEmployee.containsKey(id)) {
			ResponseEntity.notFound().build();
		}
		employeeDto.setId(id);
		allEmployee.put(id, employeeDto);
		return ResponseEntity.ok(employeeDto) ;
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		allEmployee.remove(id);
	}
	
	@PostMapping("/payraise")
	public int getPayRaise(@RequestBody Employee employee) {
		return employeeService.getPayRaisePercent(employee);
	}
}
