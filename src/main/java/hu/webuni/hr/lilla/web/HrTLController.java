package hu.webuni.hr.lilla.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hu.webuni.hr.lilla.dto.EmployeeDto;

@Controller
public class HrTLController {

	private List<EmployeeDto> allEmployee = new ArrayList<>();

	{
		allEmployee.add(new EmployeeDto(1L, "Eliza","értékesítési igazgató", 1_500_000,LocalDateTime.of(1999,4,1,1,1,1)));
		allEmployee.add(new EmployeeDto(2L, "Jani", "projektmenedzser", 800_000,  LocalDateTime.of(2002,12,1,1,1,1)));
		allEmployee.add(new EmployeeDto(3L, "Józsi", "csoportvezető", 600_000, LocalDateTime.of(2013,1,4,1,1,1)));
		allEmployee.add(new EmployeeDto(4L, "Vilma", "junior", 400_000, LocalDateTime.of(2019,12,9,1,1,1)));
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

	@GetMapping("/deleteemployee/{id}")
	public String deleteEmployee(@PathVariable long id) {
		for (Iterator<EmployeeDto> iterator = allEmployee.iterator(); iterator.hasNext();) {
			EmployeeDto employee = iterator.next();
			if (employee.getId() == id) {
				iterator.remove();
				break;
			}
		}
		return "redirect:/employees";
	}

	@GetMapping("/employees/{id}")
	public String modifyEmployee(@PathVariable long id, Map<String, Object> model) {
		model.put("employee", allEmployee.stream().filter(e -> e.getId() == id).findFirst().get());
		return "modifyemployee";
	}

	@PostMapping("/modifyemployee")
	public String updateEmployee(EmployeeDto employeeDto) {
		for (int i = 0; i < allEmployee.size(); i++) {
			if (allEmployee.get(i).getId() == employeeDto.getId()) {
				allEmployee.set(i, employeeDto);
			}
		}
		return "redirect:employees";
	}
}
