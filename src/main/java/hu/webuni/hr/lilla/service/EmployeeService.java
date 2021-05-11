package hu.webuni.hr.lilla.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import hu.webuni.hr.lilla.model.Employee;

public interface EmployeeService {
	
	public int getPayRaisePercent(Employee employee);
	
	@Transactional
	public Employee save(Employee employee);
	
	@Transactional
	public Employee update(Employee employee);
	
	public List<Employee> findAll();
	
	public Optional<Employee> findById(long id);
	
	public void delete(long id);

}


//Hozz létre egy EmployeeServiceinterfészt a service alpackage-ben, a következő metódussal:
//int getPayRaisePercent(Employee employee): megadja, hány százalékos fizetésemelés jár egy adott alkalmazottnak