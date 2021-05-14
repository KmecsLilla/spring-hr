package hu.webuni.hr.lilla.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

import hu.webuni.hr.lilla.dto.Views;

//első változat
//public class Company {
//	
//	@JsonView(Views.BaseData.class)
//	private long id;
//	
//	@JsonView(Views.BaseData.class)
//	private long registrationNumber;
//	
//	@JsonView(Views.BaseData.class)
//	private String name;
//	
//	@JsonView(Views.BaseData.class)
//	private String address;
//	
//	private List<Employee> employeesOfCompany;
//	
//	public Company(long id, long registrationNumber, String name, String address, List<Employee> employeesOfCompany) {
//		this.id = id;
//		this.registrationNumber = registrationNumber;
//		this.name = name;
//		this.address = address;
//		this.employeesOfCompany = employeesOfCompany;
//	}
//		
////	public CompanyDto(long id, long registrationNumber, String name, String address) {
////	super();
////	this.id = id;
////	this.registrationNumber = registrationNumber;
////	this.name = name;
////	this.address = address;
////	}
//	
////	private Address address;
////		"streetAddress": "21 2nd Street",
////		"city": "New York",
////		"state": "NY",
////		"postalCode": "10021"
//	
//	public Company() {	
//	}
//	
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public long getRegistrationNumber() {
//		return registrationNumber;
//	}
//
//	public void setRegistrationNumber(long registrationNumber) {
//		this.registrationNumber = registrationNumber;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public List<Employee> getEmployeesOfCompany() {
//		return employeesOfCompany;
//	}
//
//	public void setEmployeesOfCompany(List<Employee> employeesOfCompany) {
//		this.employeesOfCompany = employeesOfCompany;
//	}
//}


//2.változat SPRING DATA

@Entity
public class Company {
	
	@Id
	@GeneratedValue
	private long id;
	
	private long registrationNumber;
	
	private String name;
	
	private String address;
	
	@OneToMany(mappedBy = "company")
	private List<Employee> employeesOfCompany;
	
	public Company(long id, long registrationNumber, String name, String address, List<Employee> employeesOfCompany) {
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.address = address;
		this.employeesOfCompany = employeesOfCompany;
	}
			
//		public CompanyDto(long id, long registrationNumber, String name, String address) {
//		super();
//		this.id = id;
//		this.registrationNumber = registrationNumber;
//		this.name = name;
//		this.address = address;
//		}
		
//		private Address address;
//			"streetAddress": "21 2nd Street",
//			"city": "New York",
//			"state": "NY",
//			"postalCode": "10021"
		
	public Company() {	
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(long registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Employee> getEmployeesOfCompany() {
		return employeesOfCompany;
	}

	public void setEmployeesOfCompany(List<Employee> employeesOfCompany) {
		this.employeesOfCompany = employeesOfCompany;
	}
	
	public void addEmployeesOfCompany(Employee employee) {
		if (this.employeesOfCompany == null) {
			this.employeesOfCompany = new ArrayList<>();
			employeesOfCompany.add(employee);
			employee.setCompany(this);
		}
	}
}



//Írj egy CompanyDto osztályt, a dto alpackage-be, amely egy céget reprezentál! 
//A cégről tudjuk a cégjegyzékszámát, a nevét és címét. 
//A cégnél dolgozó alkalmazottakat egy EmployeeDto lista tartalmazza.