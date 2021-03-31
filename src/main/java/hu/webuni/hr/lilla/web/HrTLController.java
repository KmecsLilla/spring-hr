package hu.webuni.hr.lilla.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hu.webuni.hr.lilla.dto.EmployeeDto;


@Controller
public class HrTLController {

	private List<EmployeeDto> allEmployee = new ArrayList<>();
	
	{
		allEmployee.add(new EmployeeDto(18, "Eliza","értékesítési igazgató", 1_500_000,LocalDateTime.of(1999,4,1,1,1,1)));
		allEmployee.add(new EmployeeDto(14, "Jani", "projektmenedzser", 800_000,  LocalDateTime.of(2002,12,1,1,1,1)));
		allEmployee.add(new EmployeeDto(12, "Józsi", "csoportvezető", 600_000, LocalDateTime.of(2013,1,4,1,1,1)));
		allEmployee.add(new EmployeeDto(16, "Pisti", "asszisztens", 500_000, LocalDateTime.of(2017,3,5,1,1,1)));
		allEmployee.add(new EmployeeDto(20, "Vilma", "junior", 400_000, LocalDateTime.of(2019,12,9,1,1,1)));
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/employees")
	public String listEmployees(Map<String, Object> model) {
		model.put("employees", allEmployee);
		model.put("newEmployee", new EmployeeDto());
		return "employees";	
	}
	
	@PostMapping("/employees")
	public String addEmployee(EmployeeDto employee) {
		allEmployee.add(employee);
		return "redirect:employees";
	}
}
