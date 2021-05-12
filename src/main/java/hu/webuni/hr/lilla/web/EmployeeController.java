package hu.webuni.hr.lilla.web;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import hu.webuni.hr.lilla.dto.IntervalDto;
import hu.webuni.hr.lilla.mapper.EmployeeMapper;
import hu.webuni.hr.lilla.model.Employee;
import hu.webuni.hr.lilla.service.EmployeeService;
import hu.webuni.hr.lilla.service.HrEmployeeService;
import hu.webuni.hr.lilla.service.SalaryException;
import hu.webuni.hr.lilla.service.StartToWorkException;

//@RestController
//@RequestMapping("/api/employees")
//public class EmployeeController {
//
////  1.átkerül a service pack-be	
////	private Map<Long, EmployeeDto> allEmployee = new HashMap<>();
////	{
////		allEmployee.put(1L, new EmployeeDto(1L,"Eliza","értékesítési igazgató", 1_500_000,LocalDateTime.of(1999,4,1,1,1,1)));
////		allEmployee.put(2L, new EmployeeDto(2L,"Jani", "projektmenedzser", 800_000,  LocalDateTime.of(2002,12,1,1,1,1)));
////		allEmployee.put(3L, new EmployeeDto(3L,"Józsi", "csoportvezető", 600_000, LocalDateTime.of(2013,1,4,1,1,1)));
////		allEmployee.put(4L, new EmployeeDto(4L,"Pisti", "asszisztens", 500_000, LocalDateTime.of(2017,3,5,1,1,1)));
////		allEmployee.put(5L, new EmployeeDto(5L,"Vilma", "junior", 400_000, LocalDateTime.of(2019,12,9,1,1,1)));
////	}
////	
////	@Autowired
////	EmployeeService employeeService;
//	
//	@Autowired
//	HrEmployeeService hrEmployeeService;
//			
//	@Autowired
//	EmployeeMapper employeeMapper;
//	
////	1.@GetMapping
////	public List<EmployeeDto> getAll() {
////		return new ArrayList<>(allEmployee.values());
////	}
////	
////	
////	1.@GetMapping (params = "minSalary")
////	public List<EmployeeDto> getEmployeesBySalary(@RequestParam int minSalary) {
////		return allEmployee.values().stream()
////				.filter(e -> e.getSalary() > minSalary)
////				.collect(Collectors.toList());
////	}		
//	
////	1.@GetMapping
////	public List<EmployeeDto> getEmployees(@RequestParam(required = false) Integer minSalary) {
////		if (minSalary == null) {
////			return new ArrayList<>(allEmployee.values());
////		}
////		else
////			return allEmployee.values().stream()
////				.filter(e -> e.getSalary() > minSalary)
////				.collect(Collectors.toList());
////	}
//	
//	
//	@GetMapping
//	public List<EmployeeDto> getEmployees(@RequestParam(required = false) Integer minSalary) {
//		if (minSalary == null) {
//			return employeeMapper.allEmployeeToEmployeeDtos(hrEmployeeService.findAll());
//		} else {
//			return employeeMapper.allEmployeeToEmployeeDtos(hrEmployeeService.findAll()).stream()
//				.filter(e -> e.getSalary() > minSalary)
//				.collect(Collectors.toList());
//		}
//	}
//	
////	1.@GetMapping("/{id}")
////	public ResponseEntity<EmployeeDto> getById(@PathVariable long id) {
////		EmployeeDto employeeDto = allEmployee.get(id);
////		if (employeeDto != null) {
////			return ResponseEntity.ok(employeeDto);
////		}
////		else 
////			return ResponseEntity.notFound().build();
////	}
//	
//	@GetMapping("/{id}")
//	public EmployeeDto getById(@PathVariable long id) {
//		EmployeeDto employeeDto = employeeMapper.employeeToDto(hrEmployeeService.findById(id));
//		if (employeeDto != null) {
//			return employeeDto;
//		} else {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//		}			
//	}
//	
////	1. @PostMapping
////	public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
////		allEmployee.put(employeeDto.getId(), employeeDto);
////		return employeeDto;
////	}
//	
////	@PutMapping("/{id}")
////	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
////		if (!allEmployee.containsKey(id)) {
////			ResponseEntity.notFound().build();
////		}
////		employeeDto.setId(id);
////		allEmployee.put(id, employeeDto);
////		return ResponseEntity.ok(employeeDto) ;
////	}
//	
////	1. @PostMapping
////	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
////		checkStartingToWork(employeeDto);
////		checkSalary(employeeDto);
////		allEmployee.put(employeeDto.getId(), employeeDto);
////		return employeeDto;
////	}
//	
//	@PostMapping
//	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
//		checkStartingToWork(employeeDto);
//		checkSalary(employeeDto);
//		Employee employee = employeeMapper.dtoToEmployee(employeeDto);
//		hrEmployeeService.save(employee);
//		return employeeDto;
//	}
//	
////	1. @PutMapping("/{id}")
////	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) {
////		if (!allEmployee.containsKey(id)) {
////			ResponseEntity.notFound().build();
////		}
////		checkStartingToWork(employeeDto);
////		checkSalary(employeeDto);
////		employeeDto.setId(id);
////		allEmployee.put(id, employeeDto);
////		return ResponseEntity.ok(employeeDto) ;
////	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) {
//		checkStartingToWork(employeeDto);
//		checkSalary(employeeDto);
//		employeeDto.setId(id);
//		Employee employee = employeeMapper.dtoToEmployee(employeeDto);
//		employee = hrEmployeeService.modify(employee);
//		if (employee != null) {
//			employeeDto = employeeMapper.employeeToDto(employee);
//			return ResponseEntity.ok(employeeDto) ;
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//	
//	private void checkStartingToWork(EmployeeDto employeeDto) {
//		if (LocalDateTime.now().isBefore(employeeDto.getStartingToWork())) {
//			throw new StartToWorkException(employeeDto);
//		}
//	}
//	
//	private void checkSalary(EmployeeDto employeeDto) {
//		if (employeeDto.getSalary() < 0) {
//			throw new SalaryException(employeeDto);
//		}	
//	}
//	
////	1. @DeleteMapping("/{id}")
////	public void deleteEmployee(@PathVariable long id) {
////		employeeService.remove(id);
////	}
//	
//	@DeleteMapping("/{id}")
//	public void deleteEmployee(@PathVariable long id) {
//		hrEmployeeService.delete(id);
//	}
//	
////	1.@PostMapping("/payraise")
////	public int getPayRaise(@RequestBody Employee employee) {
////		return employeeService.getPayRaisePercent(employee);
////	}
//	
//	@PostMapping("/payraise")
//	public int getPayRaise(@RequestBody Employee employee) {
//		return hrEmployeeService.getPayRaisePercent(employee);
//	}



//// SPRING DATA bevezetése előtt közvetlenül:
//@RestController
//@RequestMapping("/api/employees")
//public class EmployeeController {
//	
//	@Autowired
//	HrEmployeeService hrEmployeeService;
//			
//	@Autowired
//	EmployeeMapper employeeMapper;
//	
//	@GetMapping
//	public List<EmployeeDto> getEmployees(@RequestParam(required = false) Integer minSalary) {
//		if (minSalary == null) {
//			return employeeMapper.allEmployeeToEmployeeDtos(hrEmployeeService.findAll());
//		} else {
//			return employeeMapper.allEmployeeToEmployeeDtos(hrEmployeeService.findAll()).stream()
//				.filter(e -> e.getSalary() > minSalary)
//				.collect(Collectors.toList());
//		}
//	}
//	
//	@GetMapping("/{id}")
//	public EmployeeDto getById(@PathVariable long id) {
//		EmployeeDto employeeDto = employeeMapper.employeeToDto(hrEmployeeService.findById(id));
//		if (employeeDto != null) {
//			return employeeDto;
//		} else {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//		}			
//	}
//
//	@PostMapping
//	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
//		checkStartingToWork(employeeDto);
//		checkSalary(employeeDto);
//		Employee employee = employeeMapper.dtoToEmployee(employeeDto);
//		hrEmployeeService.save(employee);
//		return employeeDto;
//	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) {
//		checkStartingToWork(employeeDto);
//		checkSalary(employeeDto);
//		employeeDto.setId(id);
//		Employee employee = employeeMapper.dtoToEmployee(employeeDto);
//		employee = hrEmployeeService.modify(employee);
//		if (employee != null) {
//			employeeDto = employeeMapper.employeeToDto(employee);
//			return ResponseEntity.ok(employeeDto) ;
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//	
//	private void checkStartingToWork(EmployeeDto employeeDto) {
//		if (LocalDateTime.now().isBefore(employeeDto.getStartingToWork())) {
//			throw new StartToWorkException(employeeDto);
//		}
//	}
//	
//	private void checkSalary(EmployeeDto employeeDto) {
//		if (employeeDto.getSalary() < 0) {
//			throw new SalaryException(employeeDto);
//		}	
//	}
//	
//	@DeleteMapping("/{id}")
//	public void deleteEmployee(@PathVariable long id) {
//		hrEmployeeService.delete(id);
//	}
//
//	@PostMapping("/payraise")
//	public int getPayRaise(@RequestBody Employee employee) {
//		return hrEmployeeService.getPayRaisePercent(employee);
//	}


//SPRING DATA bevezetése UTÁN közvetlenül:
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	HrEmployeeService hrEmployeeService;
			
	@Autowired
	EmployeeMapper employeeMapper;
	
	@GetMapping
	public List<EmployeeDto> getEmployees(@RequestParam(required = false) Integer minSalary) {
		if (minSalary == null) {
			return employeeMapper.allEmployeeToEmployeeDtos(hrEmployeeService.findAll());
		} else {
			return employeeMapper.allEmployeeToEmployeeDtos(hrEmployeeService.findBySalaryGreaterThan(minSalary));
		}
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

	@PostMapping("/filterstartingtowork")
	public List<EmployeeDto> getEmployeesByStartingToWork(@RequestBody @Valid IntervalDto intervalDto) {
		return employeeMapper
				.allEmployeeToEmployeeDtos(hrEmployeeService
						.findByStartingToWorkBetween(intervalDto.getStartDate(), intervalDto.getEndDate()));		
	}

	
	@PostMapping
	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
		Employee employee = employeeMapper.dtoToEmployee(employeeDto);
		hrEmployeeService.save(employee);
		return employeeDto;
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
