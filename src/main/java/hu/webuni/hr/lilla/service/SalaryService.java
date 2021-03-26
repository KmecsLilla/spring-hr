package hu.webuni.hr.lilla.service;

import org.springframework.stereotype.Service;

import hu.webuni.hr.lilla.model.Employee;

@Service
public class SalaryService {
	
	private EmployeeService employeeService;
	
	public SalaryService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public int getRaisedSalary(Employee employee) {
		return (int)(employee.getSalary() * (100 + employeeService.getPayRaisePercent(employee)) / 100.0);
		
	}
}


//Hozz létre egy SalaryService springes beant, amely egy metódusban beállítja egy paraméterként kapott alkalmazott új havi fizetését
//Ehhez használjon egy injektált EmployeeService beant, amelytől elkéri, hány százalék fizetésemelés jár az alkalmazottnak