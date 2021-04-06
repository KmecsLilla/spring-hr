package hu.webuni.hr.lilla.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
		allEmployee.add(new EmployeeDto(1, "Eliza","értékesítési igazgató", 1_500_000,LocalDateTime.of(1999,4,1,1,1,1)));
		allEmployee.add(new EmployeeDto(2, "Jani", "projektmenedzser", 800_000,  LocalDateTime.of(2002,12,1,1,1,1)));
		allEmployee.add(new EmployeeDto(3, "Józsi", "csoportvezető", 600_000, LocalDateTime.of(2013,1,4,1,1,1)));
		allEmployee.add(new EmployeeDto(4, "Pisti", "asszisztens", 500_000, LocalDateTime.of(2017,3,5,1,1,1)));
		allEmployee.add(new EmployeeDto(5, "Vilma", "junior", 400_000, LocalDateTime.of(2019,12,9,1,1,1)));
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
	
	@GetMapping("/modifyemployee/{id}")
	public String modifyEmployee(@PathVariable long id, Map<String, Object> model) {
		EmployeeDto foundEmployee = null;
		for (EmployeeDto employee : allEmployee) {
			if (employee.getId() == id) {
				foundEmployee = employee;
				break;
			}
		}
		model.put("employee", foundEmployee);
		return "modifyemployee";
	}
	
	@PostMapping("/modifyemployee")
	public String updateEmployee(EmployeeDto employeeDto) {
		int index = findEmployeeIndexById(employeeDto.getId());
		allEmployee.set(index, employeeDto);
		return "redirect:employees";
	}
	
	@GetMapping("/deleteemployee/{id}")
	public String deleteEmployee(@PathVariable long id) {
		int index = findEmployeeIndexById(id);
		allEmployee.remove(index);
		return "redirect:/employees";
	}
	
	protected int findEmployeeIndexById(long id) {
		int foundIndex = -1;
		for (int i = 0; i < allEmployee.size(); i++) {
			if (allEmployee.get(i).getId() == id) {
				foundIndex = i;
				break;
			}
		}
		return foundIndex;
	}

}

//3.heti gyakorlás:
//	
//Bővítsd ki a hr alkalmazásban a Thymeleaf alapú felületet az alábbi módon:
//	
//•Az alkalmazottakat listázó táblázatban az alkalmazott neve legyen egy link, ami átvisz az alkalmazott 
//szerkesztőoldalára, ahol egy form elküldésévelmódosítható az adott alkalmazott, az id-je kivételével
//
//•Az alkalmazottakat listázó táblázatban szerepeljen egy plusz oszlop, amelyben egy “Töröl” link szerepel minden sorhoz. 
//Megnyomásával értelemszerűen törlődjön a megfelelő alkalmazott a listából
