package hu.webuni.hr.lilla.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.lilla.service.SalaryService;

@RequestMapping("/api/salary")
@RestController
public class SalaryController {

	@Autowired
	SalaryService salaryService;

	@PutMapping("/{position}/raiseMin/{minSalary}")
	public void minSalary(@PathVariable String positionName, @PathVariable int minSalary) {
		salaryService.raiseMinimalSalary(positionName, minSalary);
	}
}
