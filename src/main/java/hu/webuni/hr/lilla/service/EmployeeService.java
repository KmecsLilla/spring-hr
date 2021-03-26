package hu.webuni.hr.lilla.service;

import hu.webuni.hr.lilla.model.Employee;

public interface EmployeeService {
	
	public int getPayRaisePercent(Employee employee);

}


//Hozz létre egy EmployeeServiceinterfészt a service alpackage-ben, a következő metódussal:
//int getPayRaisePercent(Employee employee): megadja, hány százalékos fizetésemelés jár egy adott alkalmazottnak